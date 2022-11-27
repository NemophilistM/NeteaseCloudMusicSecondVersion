package com.example.musiclibrary.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.musiclibrary.HOT_KEY_TABLE_NAME_DB

@Entity(tableName = HOT_KEY_TABLE_NAME_DB)
data class HotKey (
    val searchWord :String,
    val score : Int,
    val content : String,
    @PrimaryKey(autoGenerate = true)
    var id: Int
)