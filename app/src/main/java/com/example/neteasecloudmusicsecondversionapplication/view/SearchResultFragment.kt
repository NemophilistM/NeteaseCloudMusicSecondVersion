package com.example.neteasecloudmusicsecondversionapplication.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neteasecloudmusicsecondversionapplication.POST_WORD
import com.example.neteasecloudmusicsecondversionapplication.SONG_ID
import com.example.neteasecloudmusicsecondversionapplication.adapter.SearchAdapter
import com.example.neteasecloudmusicsecondversionapplication.adapter.SearchListFooterAdapter
import com.example.neteasecloudmusicsecondversionapplication.databinding.FragmentSearchResultBinding
import com.example.neteasecloudmusicsecondversionapplication.vm.SearchResultViewModel
import com.example.neteasecloudmusicsecondversionapplication.vm.SearchViewModel
import kotlinx.coroutines.launch

class SearchResultFragment : Fragment() {
    private lateinit var binding: FragmentSearchResultBinding
    private lateinit var  viewModel:SearchResultViewModel
    private val adapter: SearchAdapter = SearchAdapter(){

        // 将当前曲目存入播放列表
        viewModel.saveCurrentSong(it)

        val intent = Intent(requireActivity(), PlayerActivity::class.java)
        intent.putExtra(SONG_ID,it.id)
        startActivity(intent)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchResultBinding.inflate(inflater)
        val args: Bundle = requireArguments()
        val word = args.getString(POST_WORD)
        val recyclerView: RecyclerView = binding.rvSongList
        viewModel = ViewModelProvider(this)[SearchResultViewModel::class.java]
        //初始化适配器
        recyclerView.adapter = adapter.withLoadStateFooter(SearchListFooterAdapter{adapter.retry()})
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        lifecycleScope.launch{
            //之所以判断一定非空，是因为在碎片跳转之前就回先对et进行判断，既然跳转了那一定非空
            viewModel.getData(word!!).collect{
                adapter.submitData(it)
            }
        }
        adapter.addLoadStateListener {
            when(it.refresh){
                is LoadState.NotLoading ->{
                    binding.pbSearch.visibility = View.INVISIBLE
                    recyclerView.visibility = View.VISIBLE
                }
                is LoadState.Loading ->{
                    binding.pbSearch.visibility = View.VISIBLE
                    recyclerView.visibility = View.INVISIBLE
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    binding.pbSearch.visibility = View.INVISIBLE
                    Toast.makeText(requireActivity(), "Load Error: ${state.error.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val list = adapter.snapshot()
        //这一部分是原本用于下拉刷新的
//        binding.srSearch.setColorSchemeColors(
//            Color.parseColor("#00FF80"),
//            Color.parseColor("#CEF6D8")
//        )
//        binding.srSearch.setOnRefreshListener {
//            adapter.retry()
//        }
        return binding.root
    }

    /**
     * 点击函数，传递id给逻辑层搜索
     */
    val click =  {


    }


}