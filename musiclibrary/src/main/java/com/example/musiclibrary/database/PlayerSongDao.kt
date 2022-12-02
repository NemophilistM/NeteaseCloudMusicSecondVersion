package com.example.musiclibrary.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.musiclibrary.entity.PlayerSong
import com.example.musiclibrary.util.ListTypeConverters

@Dao
@TypeConverters(ListTypeConverters::class)
interface PlayerSongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg data: PlayerSong )

    @Query("select songName from PlayerSong  ")
    fun getSongName(): List<String>

    @Query("select * from PlayerSong where id = :id")
    fun querySong(id:Int):PlayerSong?

    @Query("select songId from PlayerSong ")
    fun getSongId(): LiveData<List<Long>>

    @Query("select artist from PlayerSong  ")
    fun getArtist(): LiveData<List<String>>

    @Query("select album from PlayerSong ")
    fun getAlbum(): LiveData<List<String>>

    @Query("select * from PlayerSong ")
    fun getAll(): LiveData<List<PlayerSong>>

    @Delete
    fun delete(vararg song: PlayerSong )

    @Query("select count(*) from PlayerSong ")
    fun getCount(): Int?
}