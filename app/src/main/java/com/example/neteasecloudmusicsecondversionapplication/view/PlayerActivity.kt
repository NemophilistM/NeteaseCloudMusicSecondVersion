package com.example.neteasecloudmusicsecondversionapplication.view

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import android.os.Bundle
import android.os.IBinder
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.neteasecloudmusicsecondversionapplication.R
import com.example.neteasecloudmusicsecondversionapplication.SONG_ID
import com.example.neteasecloudmusicsecondversionapplication.databinding.ActivityPlayerBinding
import com.example.neteasecloudmusicsecondversionapplication.util.TimeUtil
import com.example.neteasecloudmusicsecondversionapplication.vm.PlayerViewModel
import com.example.playerservice.PlayerService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import java.util.Timer
import java.util.TimerTask

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    private var playerService: PlayerService? = null
    private var songId: Int? = null
    private var isBind = false
    private lateinit var timer: Timer
    private lateinit var seekBar: SeekBar
    private var isSeekbarChanging: Boolean = false
    private var currentPosition: Int? = null
    private lateinit var viewModel: PlayerViewModel

    //获取当前播放列表曲目数量
    private var count: Int? = null

    // 当前歌曲的在播放列表的位置
    private var currentSongPosition: Int = 0

    //服务链接类
    private var serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            println("onServiceConnected: ${Thread.currentThread().name}")
            isBind = true
            val myBinder = service as PlayerService.InnerBinder
            playerService = myBinder.service
            if (songId != null && songId != 0) {
                binding.ivStop.setImageResource(R.drawable.ic_continue)
                playerService!!.play(songId!!)
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBind = false
        }
    }

    //判断是否为第一次进入，默认为是
    private var isFirstInside = true

    //判断是否正在播放，默认为否
    private var isPlaying: Boolean? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 将状态栏修改为灰色
        val window = this.window
        window.statusBarColor = Color.GRAY

        //初始化顶部栏
        initToolbar()

        //绑定viewmodel
        viewModel = ViewModelProvider(this)[PlayerViewModel::class.java]

        // 获取id，如果有传入的话，如果没有传入则代表直接进入播放界面的
        val intent = intent
        songId = intent.getIntExtra(SONG_ID, 0)


        timer = Timer()

        // 绑定服务
        initService()

        //发出申请获取播放列表数据
        lifecycleScope.launch(Dispatchers.IO) {
            count = viewModel.requestCount()
            if (count != null){
                // 特别说明：此步骤在添加曲目成功之后
                currentSongPosition = count!!
            }else{
                toast("播放列表异常，请检查是否登录")
            }
        }
        //界面ui的初始化更新
        binding.ivStop.setImageResource(R.drawable.ic_stop)
        binding.ivLastSong.setImageResource(R.drawable.ic_last_song)
        binding.ivNextSong.setImageResource(R.drawable.ic_next_song)

        //监听滚动条事件
        seekBar = binding.sbProgress
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            //滚动时暂停后台计时器
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                isSeekbarChanging = true
            }

            //滚动结束后重新设置值
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                isSeekbarChanging = false
                if (seekBar != null) {
                    playerService?.seekTo(seekBar.progress)
                }
            }

        })


        //点击事件的监听
        // 点击暂停与播放
        binding.ivStop.setOnClickListener {

            isPlaying = playerService?.mediaPlayer?.isPlaying
            when (isPlaying) {
                null -> println("PlayerActivity:mediaPlayer is null")
                true -> {
                    currentPosition = playerService?.mediaPlayer?.currentPosition
                    // 暂停播放
                    playerService?.pause()
                    binding.ivStop.setImageResource(R.drawable.ic_stop)
                    lifecycleScope.launch {
                        playerService?.playerState?.collect() {
                            if (it == 2) {
                                //移除计时器所有任务
                                timer.purge()
                            }
                        }
                    }

                }
                false -> {
                    playerService?.continuePlay()
                    lifecycleScope.launch {
                        playerService?.playerState?.collect() {
                            if (it == 1) {
                                // 给timer添加新任务
                                timer.schedule(object : TimerTask() {
                                    override fun run() {
                                        if (isSeekbarChanging == false) {
                                            runOnUiThread {
                                                seekBar.progress =
                                                    playerService?.mediaPlayer?.currentPosition!!
                                                binding.tvProgress.text =
                                                    TimeUtil.time(playerService?.mediaPlayer?.currentPosition!!)
                                            }

                                        }
                                    }
                                }, 0, 50)
                            }
                        }
                    }

                    binding.ivStop.setImageResource(R.drawable.ic_continue)
                }
            }

        }

        // 点击下一首
        binding.ivNextSong.setOnClickListener {
            lifecycleScope.launch {
                playerService?.playerLogic?.collect() {
                    when (it) {
                        0 -> {
                            if (currentSongPosition == 0) {
                                runOnUiThread {
                                    toast("当前播放列表并无歌曲，请添加歌曲")
                                }
                            } else if (currentSongPosition == 1) {
                                currentSongPosition = count!!
                            } else {
                                currentSongPosition--
                            }
                            playerService?.playPosition(currentSongPosition)
                            }
                        1->{

                        }
                        2->{

                        }
                    }
                }
            }
        }

        // 点击上一首
        binding.ivLastSong.setOnClickListener {
            lifecycleScope.launch{
                playerService?.playerLogic?.collect(){
                    when (it) {
                        0 -> {
                            if (currentSongPosition == 0) {
                                runOnUiThread {
                                    toast("当前播放列表并无歌曲，请添加歌曲")
                                }
                            } else if (currentSongPosition == count) {
                                currentSongPosition = 1
                            } else {
                                currentSongPosition++
                            }
                            playerService?.playPosition(currentSongPosition)
                        }
                        1->{

                        }
                        2->{

                        }
                    }
                }
            }

        }

//        playerService?.liveData?.observe(this, Observer {
//            timer.schedule(object :TimerTask(){
//                override fun run() {
//                    if(isSeekbarChanging == false){
//                        //之所以判断非空是因为既然已经发送信号了就代表已经准备完毕，则一定有数据
//                        seekBar.progress = playerService?.mediaPlayer?.currentPosition!!
//                        seekBar.max = playerService?.mediaPlayer?.duration!!
//                    }
//                }
//            },0,50)
//        })

        //开启协程监听数据，当player准备完毕之后开始进行拖动条
//        lifecycleScope.launch {
//            playerService?.mutableSharedFlow?.collect(){
//                timer.schedule(object :TimerTask(){
//                    override fun run() {
//                        if(isSeekbarChanging == false){
//                            //之所以判断非空是因为既然已经发送信号了就代表已经准备完毕，则一定有数据
//                            seekBar.progress = playerService?.mediaPlayer?.currentPosition!!
//                            seekBar.max = playerService?.mediaPlayer?.duration!!
//                        }
//                    }
//                },0,50)
//            }
//
//        }
        lifecycleScope.launch {
            var state: Int? = null
            do {
                delay(1000)
                playerService?.playerState?.collect() {
                    state = it
                    if (it == 1) {
                        timer.schedule(object : TimerTask() {
                            override fun run() {
                                if (!isSeekbarChanging) {
                                    //之所以判断非空是因为既然已经发送信号了就代表已经准备完毕，则一定有数据
                                    println("TIME: ${Thread.currentThread().name}")
                                    runOnUiThread {
                                        seekBar.progress =
                                            playerService?.mediaPlayer?.currentPosition!!
                                        seekBar.max = playerService?.mediaPlayer?.duration!!
                                        binding.tvProgress.text =
                                            TimeUtil.time(playerService?.mediaPlayer?.currentPosition!!)
                                        binding.tvTotal.text =
                                            TimeUtil.time(playerService?.mediaPlayer?.duration!!)
                                    }


                                }
                            }
                        }, 0, 50)
                    }
                }
            } while (state != 1)
        }


    }

    private fun initService() {
        val serviceIntent = Intent(this, PlayerService::class.java)

        // 由于serviceConnect创建是在绑定之前，故它一定不为空
        bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE)
    }

    private fun initToolbar() {
        val toolbar = binding.tbPlayer
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_return)
        }
    }


    //对顶部栏的逻辑实现
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {

        }
        return true
    }

    override fun onDestroy() {
        unbindService(serviceConnection)
        timer.cancel()
        //此处有一个bug，当我关闭再次进去的时候，这个由于timer是非空，可能会导致报错
//        timer = null
        super.onDestroy()
    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}