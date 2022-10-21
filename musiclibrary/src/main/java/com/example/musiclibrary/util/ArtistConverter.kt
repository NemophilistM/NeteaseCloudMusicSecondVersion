package com.example.musiclibrary.util

import androidx.room.TypeConverter
import com.example.musiclibrary.entity.Artist

class ArtistConverter {
    @TypeConverter
    fun toDataType(artistString: String): List<Artist> {
        val string: List<String> = artistString.split(",")
        val list: MutableList<Artist> = mutableListOf()
        var i = 0
        var j = 0
        while (i < string.size) {
            list.add(j, Artist(string[i].toLong(), string[i + 1], string[i + 2]))
            i = +3
            j++
        }
       return list

    }

    @TypeConverter
    fun toIndex(dataType: List<Artist>): String {
        val string = StringBuilder()
        for (artist in dataType) {
            val idString: String = artist.id.toString()
            string.append(idString).append(",")
        }

        return string as String
    }
}