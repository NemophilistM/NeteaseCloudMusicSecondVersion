package com.example.musiclibrary.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.musiclibrary.REQUEST_PAGE_MAX
import com.example.musiclibrary.entity.PlayerSong
import com.example.musiclibrary.entity.Song
import com.example.musiclibrary.pagingsource.SearchSongPagingSource
import com.example.musiclibrary.source.SearchListNetDatasource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

object SearchListRepository {

    private val datasource = SearchListNetDatasource()

//    suspend fun getList(passWord: String, curPage: Int,isRefreshL:Boolean): List<SearchSong> {
//
//        if(isRefreshL){
//            datasource.removeAllLocal()
//        }
//        //根据传入内容从数据库中获取数据并进行强转
//        val list: MutableList<SearchSong>? =
//            datasource.getListFromLocal(ALL_ELEMENT_DB) as MutableList<SearchSong>?
////        var listData = listOf<SearchSong>()
//        //进行判断，如果本地没有存储，则直接存储，如果本地存储了，则在其后加上
//        val listData = if (list == null) {
//            withContext(Dispatchers.IO) {
//                val songs = datasource.getListFromNet(passWord, curPage)
//                songs
//            }
//        } else {
//            val data = withContext(Dispatchers.IO) {
//                val songs = datasource.getListFromNet(passWord, curPage)
//                songs
//            }
//            list.addAll(data)
//            list
//        }
//        return listData
//
//    }


    fun getData(passWord: String) : Flow<PagingData<Song>>{
        return Pager(
            config = PagingConfig(REQUEST_PAGE_MAX),
            pagingSourceFactory = {SearchSongPagingSource(datasource,passWord)}
        ).flow
    }

    fun play() {
        TODO("Not yet implemented")
    }

    suspend fun addElement(song: PlayerSong) {
        PlayerListRepository.addElement(song)
    }




}