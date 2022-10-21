package com.example.musiclibrary.repository

import com.example.musiclibrary.database.DatabaseManager
import com.example.musiclibrary.entity.SearchSong
import com.example.musiclibrary.source.SearchListNetDatasource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchListRepository{

    /**
     * 没有捕获网络异常，将异常上抛给ui层捕获之后展示
     */
    suspend fun getList(passWord:String,curPage:Int): List<SearchSong> {
       return withContext(Dispatchers.IO) {
           val datasource = SearchListNetDatasource()
           val songs = datasource.getListFromNet(passWord,curPage)
           DatabaseManager.db.searchSongDao.save(*songs.toTypedArray())
           songs
       }
    }

     fun play() {
        TODO("Not yet implemented")
    }

    fun addElement(){
        TODO("Not yet implemented")
    }
}