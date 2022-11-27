package com.example.musiclibrary.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.musiclibrary.entity.HotKey
import com.example.musiclibrary.entity.PlayerSong
import com.example.musiclibrary.entity.SearchSong
import com.example.musiclibrary.util.ArtistConverter
import com.example.musiclibrary.util.ListTypeConverters

@Database(version = 1,exportSchema = false,entities = [HotKey::class, SearchSong::class,PlayerSong::class])
@TypeConverters(ListTypeConverters::class)
abstract class MusicDatabase :RoomDatabase() {
    abstract fun createHotKeyDao():HotKeyDao
    val hotKeyDao : HotKeyDao by lazy { createHotKeyDao() }
    abstract fun createSearchSongDao():SearchSongDao
    val searchSongDao : SearchSongDao by lazy { createSearchSongDao() }
    abstract fun createPlayerSongDao():PlayerSongDao
    val playerSongDao : PlayerSongDao by lazy { createPlayerSongDao() }
}