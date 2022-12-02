package com.example.musiclibrary.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.musiclibrary.SEARCH_TABLE_NAME_DB
import com.example.musiclibrary.util.AlbumConverter
import com.example.musiclibrary.util.ArtistConverter
import com.example.musiclibrary.util.ListTypeConverters


// 搜索列表的参数数据类
@Entity(tableName = SEARCH_TABLE_NAME_DB)
@TypeConverters(ListTypeConverters::class)
data class SearchSong(
    @PrimaryKey
    val songId: Int,

    val songName: String,

    val artist: List<String>,

    val album: List<String>,
)
