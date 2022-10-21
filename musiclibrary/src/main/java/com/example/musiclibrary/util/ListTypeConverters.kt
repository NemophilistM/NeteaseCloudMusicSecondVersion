package com.example.musiclibrary.util

import androidx.room.TypeConverter

class ListTypeConverters {
    @TypeConverter
    fun getThumbFromString(value: String):List<String> {
        return value.split(",")
    }

    @TypeConverter
    fun storeThumbToString(list: List<String>): String {
        val str = StringBuilder(list[0])
        list.forEach {
            str.append(",").append(it)
        }
        return str.toString()
    }
}