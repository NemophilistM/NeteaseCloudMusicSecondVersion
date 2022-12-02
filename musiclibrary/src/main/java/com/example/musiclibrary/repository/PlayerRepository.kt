package com.example.musiclibrary.repository

import androidx.lifecycle.liveData
import com.example.musiclibrary.entity.PlayerSong
import com.example.musiclibrary.entity.Song
import com.example.musiclibrary.source.PlayerDatasource
import kotlinx.coroutines.Dispatchers

object PlayerRepository {
    private val datasource = PlayerDatasource()

    suspend fun getUrl(id: Int,passUrl :(String?)->Unit) {
        val url = datasource.getUrl(id)
        if(url.code == 200){
            passUrl(url.data[0].url)
        }else{
            println("Error:NOT GetUrl")
        }
//        passUrl(url.data)
    }
    suspend fun getSong(position: Int,passUrl: (String?) -> Unit) {
        val song = datasource.getSongFromLocal(position)
        if(song!=null){
            val url = datasource.getUrl(song.songId)
            passUrl(url.data[0].url)
        }else{
            println("PlayerRepository:getSong:SONG is NULL")
        }

    }
}