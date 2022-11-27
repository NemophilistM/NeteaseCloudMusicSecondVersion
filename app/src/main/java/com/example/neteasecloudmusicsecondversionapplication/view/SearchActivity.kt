package com.example.neteasecloudmusicsecondversionapplication.view

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.musiclibrary.entity.HotKeyJson
import com.example.neteasecloudmusicsecondversionapplication.INPUT_NULL_TIPS
import com.example.neteasecloudmusicsecondversionapplication.POST_WORD
import com.example.neteasecloudmusicsecondversionapplication.R
import com.example.neteasecloudmusicsecondversionapplication.databinding.ActivitySearchBinding
import com.example.neteasecloudmusicsecondversionapplication.vm.SearchViewModel


class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private var searchResultFragment: SearchResultFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 将状态栏修改为灰色
        val window = this.window
        window.statusBarColor = Color.GRAY

        initToolBar()

        // 初始化vm
        val viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        // 开启协程获取网络数据
        viewModel.requestHotKet(true)


        // 观察数据进行响应式更新
//        viewModel.hotKeyLiveData.observe(this) { hotSongList ->
//            binding.flowLayoutHotSong.visibility = View.VISIBLE
//            for (i in hotSongList.indices) {
//                val textView = TextView(this)
//                textView.text = hotSongList[i].searchWord
//                textView.setBackgroundResource(R.drawable.ripple_onclick_hot_song)
//                // 告知父布局要怎么布局
//                textView.layoutParams =
//                    ViewGroup.LayoutParams(
//                        ViewGroup.LayoutParams.WRAP_CONTENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT
//                    )
//                val padding = 10
//                textView.setPadding(padding, padding, padding, padding)
//                val word: String = hotSongList[i].searchWord
//                textView.setOnClickListener {
//                    binding.etWidgetSearch.setText(word)
//
//                    searchResultFragment = SearchResultFragment().apply {
//                        val args = Bundle()
//                        args.putString(POST_WORD, word)
//                        arguments = args
//                    }
//                    replaceFragment()
//
//                }
//                binding.flowLayoutHotSong.addView(textView)
//            }
//        }

        binding.flowLayoutHotSong.visibility = View.GONE
        viewModel.hotKeyLiveData.observe(this, Observer { result ->
            val hotkey = result.getOrNull()
            if (hotkey != null) {
                binding.flowLayoutHotSong.visibility = View.VISIBLE
                for (i in hotkey.indices) {
                    val textView = TextView(this)
                    textView.text = hotkey[i].searchWord
                    textView.setBackgroundResource(R.drawable.ripple_onclick_hot_song)
                    // 告知父布局要怎么布局
                    textView.layoutParams =
                        ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                    val padding = 10
                    textView.setPadding(
                        padding, padding, padding, padding
                    )
                    val word: String = hotkey[i].searchWord!!
                    textView.setOnClickListener {
                        binding.etWidgetSearch.setText(word)

                    }
                    binding.flowLayoutHotSong.addView(textView)

                }

            }
        })
}

private fun initToolBar() {
    val toolbar = findViewById<Toolbar>(R.id.tb_search)
    setSupportActionBar(toolbar)
    val actionBar = supportActionBar
    actionBar?.setDisplayHomeAsUpEnabled(true)
    actionBar?.setHomeAsUpIndicator(R.drawable.ic_return)
}

// tb相关操作
override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_base_activity_toolbar, menu)
    return true
}

// tb点击反馈
override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
        R.id.search_toolbar -> {
            val text = binding.etWidgetSearch.getEditText()
            if (text.isEmpty()) {
                Toast.makeText(this, INPUT_NULL_TIPS, Toast.LENGTH_SHORT).show()
            } else {
                if (searchResultFragment == null) {
                    searchResultFragment = SearchResultFragment().apply {
                        val args = Bundle()
                        args.putString(POST_WORD, text)
                        arguments = args
                    }
                    replaceFragment()
                } else {
                    removeFragment()
                    searchResultFragment = SearchResultFragment().apply {
                        val args = Bundle()
                        args.putString(POST_WORD, text)
                        arguments = args
                    }
                    replaceFragment()
                }
            }
        }

        android.R.id.home -> if (searchResultFragment != null) {
            removeFragment()
        } else {
            finish()
        }
    }
    return true
}

private fun replaceFragment() {
    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    searchResultFragment?.let { transaction.add(R.id.layout_search, it) }
    transaction.commit()
}


private fun showFragment() {
    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    searchResultFragment?.let { transaction.show(it) }
    transaction.commit()
}

private fun removeFragment() {
    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    searchResultFragment?.let { transaction.remove(it) }
    transaction.commit()
    searchResultFragment = null
}
}