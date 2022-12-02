package com.example.musiclibrary.source

import androidx.lifecycle.LiveData
import com.example.musiclibrary.database.DatabaseManager
import com.example.musiclibrary.entity.PlayerSong

class PlayerListLocalDatasource {

    fun getList(): LiveData<List<PlayerSong>> {
        return DatabaseManager.db.playerSongDao.getAll()
    }

    suspend fun addElement(song: PlayerSong) {
        DatabaseManager.db.playerSongDao.save(song)
    }

    suspend fun getCount():Int?{
        return  DatabaseManager.db.playerSongDao.getCount()
    }
}