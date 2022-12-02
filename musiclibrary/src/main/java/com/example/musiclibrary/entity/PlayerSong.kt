package com.example.musiclibrary.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.musiclibrary.PLAYER_TABLE_NAME_DB
import com.example.musiclibrary.util.ListTypeConverters

// 搜索列表的参数数据类
@Entity(tableName = PLAYER_TABLE_NAME_DB, indices =[Index(value = ["songId"],unique = true)] )
@TypeConverters(ListTypeConverters::class)
data class PlayerSong(
    val songName: String,
    val songId: Int,
    val artist: List<String>,
    val album: List<String>,
    @PrimaryKey
    val id: Int? = null

)
