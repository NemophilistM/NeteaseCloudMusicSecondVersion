package com.example.playerservice

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.os.Binder
import android.os.IBinder
import androidx.lifecycle.MutableLiveData
import com.example.musiclibrary.entity.PlayerSong
import com.example.musiclibrary.repository.PlayerRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.io.IOException
import kotlin.math.log


class PlayerService : Service() {
    //音乐播放器
    var mediaPlayer: MediaPlayer? = null

    /**
     * 参数说明：0-> 顺序播放
     *         1-> 随机播放
     *         2-> 单曲循环
     */
    private var mPlayerLogic:Int = 0;

    private val binder: PlayerService.InnerBinder = InnerBinder()


    // 以下的flow用于对部分数据和状态的更新
    /**
     * player状态Flow
     * 状态参数：
     * 0：player为空
     * 1：player正在播放
     * 2：player暂停播放，但不为空
     *
     */
    val playerState : Flow<Int> = flow {
        when(mediaPlayer?.isPlaying){
            null->emit(0)
            true->emit(1)
            false->emit(2)
        }
    }

    /**
     *  currentPosition Flow
     *  参数：0：player为空
     *  除此之外为player 当前播放位置
     */
    val progress:Flow<Int> = flow {
        if(mediaPlayer!=null){
            emit(mediaPlayer?.currentPosition!!)
        }else{
            emit(0)
        }
    }

    /**
     * 获取播放器的总时长
     * 参数：0：plauer为空
     * 除此之外为player当当前播放曲目的总时长
     */
    val durtion :Flow<Int> = flow {
        if(mediaPlayer!=null){
            emit(mediaPlayer?.duration!!)
        }else{
            emit(0)
        }
    }

    val playerLogic :Flow<Int> = flow {
        emit(mPlayerLogic)
    }


    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()
        // 播放器的监听方法，判断是否准备完毕，准备完毕会调用前面的回调方法对view层进行通知
        mediaPlayer?.setOnPreparedListener(OnPreparedListener { mp: MediaPlayer ->
            mediaPlayer = mp
            mediaPlayer?.start()
        })
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    fun play(id: Int) {
        MainScope().launch {
            withContext(Dispatchers.IO) {
                PlayerRepository.getUrl(id) { url ->
                    mediaPlayer?.let {
                        it.reset()
                        try {
                            it.setDataSource(url)
                            it.prepareAsync()
                        } catch (e: IOException) {
                            println(e.message)
                        }

                    }
                }
            }


        }

    }

    fun playPosition(position:Int) {
        MainScope().launch {
            withContext(Dispatchers.IO){
                PlayerRepository.getSong(position){url->
                    mediaPlayer?.let {
                        it.reset()
                        try {
                            it.setDataSource(url)
                            it.prepareAsync()
                        } catch (e: IOException) {
                            println(e.message)
                        }

                    }
                }
            }
        }
    }


    fun seekTo(progress: Int) {
        MainScope().launch {
            withContext(Dispatchers.IO) {
                mediaPlayer?.seekTo(progress)
            }
        }
    }

    fun stop() {
        MainScope().launch {
            withContext(Dispatchers.IO) {
                mediaPlayer?.stop()
            }
        }
    }


    fun pause() {
        MainScope().launch {
            withContext(Dispatchers.IO) {
                mediaPlayer?.pause()
            }
        }
    }

    fun continuePlay() {
        MainScope().launch {
            withContext(Dispatchers.IO) {
                mediaPlayer?.start()
            }
        }
    }

    fun changeLogic(logic:Int){
        mPlayerLogic = logic
    }


    inner class InnerBinder : Binder() {
        val service: PlayerService
            get() = this@PlayerService
    }


}