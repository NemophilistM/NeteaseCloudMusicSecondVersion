package com.example.musiclibrary.source

import com.example.musiclibrary.database.DatabaseManager
import com.example.musiclibrary.network.RequestNetWork

class PlayerDatasource {
    suspend fun getSongFromLocal(position : Int) = DatabaseManager.db.playerSongDao.querySong(position)
    suspend fun getUrl(id: Int) = RequestNetWork.musicService.getMusicUrl(id)
}