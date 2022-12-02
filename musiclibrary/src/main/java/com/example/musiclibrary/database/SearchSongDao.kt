package com.example.musiclibrary.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.musiclibrary.entity.Album
import com.example.musiclibrary.entity.Artist
import com.example.musiclibrary.entity.HotKey
import com.example.musiclibrary.entity.SearchSong
import com.example.musiclibrary.util.AlbumConverter
import com.example.musiclibrary.util.ArtistConverter
import com.example.musiclibrary.util.ListTypeConverters

@Dao
@TypeConverters(ListTypeConverters::class)
interface SearchSongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg data: SearchSong)

    @Query("select songName from SearchSong  ")
    fun getSongName(): LiveData<List<String>>

    @Query("select songId from SearchSong  ")
    fun getSongId(): LiveData<List<Int>>

    @Query("select artist from SearchSong ")
    fun getArtist(): LiveData<List<String>>

    @Query("select album from SearchSong ")
    fun getAlbum(): LiveData<List<String>>

    @Query("select * from SearchSong ")
    fun getAll(): LiveData<List<SearchSong>>

    @Delete
    fun delete(vararg song: SearchSong)

    @Query("delete from SearchSong")
    fun deleteAll()

    
}