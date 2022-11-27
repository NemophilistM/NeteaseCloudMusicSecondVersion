package com.example.musiclibrary.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.musiclibrary.entity.PlayerSong
import com.example.musiclibrary.util.ListTypeConverters

@Dao
@TypeConverters(ListTypeConverters::class)
interface PlayerSongDao {
    @Insert
    fun save(vararg data: PlayerSong )

    @Query("select songName from PlayerSong order by id ")
    fun getSongName(): LiveData<List<String>>

    @Query("select songId from PlayerSong  order by id ")
    fun getSongId(): LiveData<List<Long>>

    @Query("select artist from PlayerSong  order by id")
    fun getArtist(): LiveData<List<String>>

    @Query("select album from PlayerSong  order by id")
    fun getAlbum(): LiveData<List<String>>

    @Query("select * from PlayerSong  order by id")
    fun getAll(): LiveData<List<PlayerSong>>

    @Delete
    fun delete(vararg song: PlayerSong )
}