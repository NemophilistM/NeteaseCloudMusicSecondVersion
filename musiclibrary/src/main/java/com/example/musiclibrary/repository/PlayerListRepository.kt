package com.example.musiclibrary.repository

import androidx.lifecycle.LiveData
import com.example.musiclibrary.entity.PlayerSong
import com.example.musiclibrary.entity.SearchSong
import com.example.musiclibrary.source.PlayerListLocalDatasource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object PlayerListRepository {

    private val datasource = PlayerListLocalDatasource()

    suspend fun getList(): LiveData<List<PlayerSong>> {
        return withContext(Dispatchers.IO) {
            datasource.getList()
        }
    }

    fun play(position: Int) {
        TODO("Not yet implemented")
    }

    // 用于整体更新播放列表
    fun upDataList(newList: List<SearchSong>) {
        TODO("Not yet implemented")
    }

    // 单一删除某一元素的接口
    fun deleteElement() {
        TODO("Not yet implemented")
    }

    // 下载接口
    fun downloadElement() {
        TODO("Not yet implemented")
    }


    //添加歌曲接口
    suspend fun addElement(song: PlayerSong) {
        withContext(Dispatchers.IO) {
            datasource.addElement(song)
        }
    }

}