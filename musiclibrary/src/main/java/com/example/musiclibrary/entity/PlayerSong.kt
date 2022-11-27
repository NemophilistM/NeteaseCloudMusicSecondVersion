package com.example.musiclibrary.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.musiclibrary.PLAYER_TABLE_NAME_DB
import com.example.musiclibrary.SEARCH_TABLE_NAME_DB
import com.example.musiclibrary.util.ListTypeConverters

// 搜索列表的参数数据类
@Entity(tableName = PLAYER_TABLE_NAME_DB)
@TypeConverters(ListTypeConverters::class)
data class PlayerSong(
    val songName: String = "noSongName",
    val songId: Long = 0,
    val artist: List<String> = listOf(),
    val album: List<String> = listOf(),
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
