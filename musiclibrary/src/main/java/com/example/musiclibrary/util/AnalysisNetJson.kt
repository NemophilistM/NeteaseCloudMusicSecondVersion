package com.example.musiclibrary.util

import com.example.musiclibrary.*
import com.example.musiclibrary.entity.*
import org.json.JSONObject

object AnalysisNetJson {
    fun analysisSearchList(responseData: String): List<SearchSong> {
        var code = 0
        val mutableSongList = mutableListOf<SearchSong>()
        runCatching {
            val root = JSONObject(responseData)
            code = root.getInt(CODE_JSON)
            if (code == SUCCESS_CODE_JSON) {
                val result = root.getJSONObject(RESULT_JSON)
                val songs = result.getJSONArray(SONG_JSON)
                for (i in 0 until songs.length()) {
                    val song = songs.getJSONObject(i)
                    //艺术家的解析与记录
                    val artistsObject = song.getJSONArray(ARTIST_JSON)
                    //专辑的解析与记录
                    val albumObject = song.getJSONObject(ALBUM_JSON)
//                    val album = Album(
//                        albumObject.getLong(ID_JSON),
//                        albumObject.getString(NAME_JSON),
//                        albumObject.getString(PIC_URL_JSON)
//                    )
                    val albumList = listOf<String>(albumObject.getString(NAME_JSON),albumObject.getString(PIC_URL_JSON))


                    val artists: MutableList<String> = mutableListOf()
                    for (j in 0 until artistsObject.length()) {
                        val art = artistsObject.getJSONObject(j)
//                        val artist = Artist(
//                            art.getLong(ID_JSON),
//                            art.getString(NAME_JSON),
//                            art.getString(ALIAS_JSON)
//                        )
                        artists.add(art.getString(NAME_JSON))
                        artists.add(art.getString(ALIAS_JSON))
                    }

                    val songData = SearchSong(
                        //歌曲id和名字的记录
                        song.getString(NAME_JSON),
                        song.getLong(ID_JSON),
                        artists,
                        albumList,
                        id = 0
                    )
                    mutableSongList.add(songData)

                }
            }
        }.onFailure {
            it.printStackTrace()
        }
        return mutableSongList
    }
    fun analysisHotKey(responseData: String):List<HotKey>{
        var code  = 0
        var errorMsg = "success"
        val hotSong: MutableList<HotKey> = mutableListOf()
        runCatching {
            val root = JSONObject(responseData)
            code = root.getInt(CODE_JSON)
            errorMsg = root.getString(MESSAGE_JSON)
            if (code == SUCCESS_CODE_JSON) {
                val hots = root.getJSONArray(HOTS_DETAIL_DATA_JSON)
                for (i in 0 until hots.length()) {
                    val hot = hots.getJSONObject(i)
                    val hotKey = HotKey(
                        hot.getString(HOTS_DETAIL_DATA_SEARCH_WORD_JSON),
                        hot.getInt(HOTS_DETAIL_DATA_SCORE_JSON),
                        hot.getString(HOTS_DETAIL_DATA_CONTENT_JSON),
                        id = 0
                        )
                    hotSong.add(hotKey)
                }
            }
        }.onFailure {
            it.printStackTrace()
            println(errorMsg)
        }
        return hotSong
    }
}