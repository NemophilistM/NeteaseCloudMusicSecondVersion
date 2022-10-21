package com.example.musiclibrary.util

import androidx.room.TypeConverter
import com.example.musiclibrary.entity.Album
import com.example.musiclibrary.entity.Artist

class AlbumConverter {
    @TypeConverter
    fun toDataType(albumString: String): Album {
        val string: List<String> = albumString.split(",")
        return Album(string[0].toLong(),string[1],string[2])
    }

    @TypeConverter
    fun toIndex(dataType: Album): String {
        val idString: String = dataType.id.toString()
        return "${idString},${dataType.name},${dataType.picUrl}"
    }
}