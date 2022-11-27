package com.example.musiclibrary.source

import android.provider.SyncStateContract
import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.example.musiclibrary.*
import com.example.musiclibrary.database.DatabaseManager
import com.example.musiclibrary.entity.SearchSong
import com.example.musiclibrary.entity.SearchSongJson
import com.example.musiclibrary.network.RequestNetWork
import com.example.musiclibrary.util.AnalysisNetJson
import okhttp3.*

class SearchListNetDatasource {

    //使用的okhttp尝试进行的网络请求
//    fun getListFromNet(passWord: String, curPage: Int): List<SearchSong> {

//        val okHttpClient = OkHttpClient()
//        val requestBody = FormBody.Builder()
//            .add(KEY_WORD_PASSWORD, passWord)
//            .add(OFFSET_PASSWORD, ((curPage - 1) * REQUEST_PAGE_MAX).toString())
//            .build()
//        val request = Request.Builder()
//            .url(BASE_URL)
//            .post(requestBody)
//            .build()

//        okHttpClient.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                e.printStackTrace()
//                onFailure(e)
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                val responseData = response.body()?.string()
//                val songList = responseData?.let { AnalysisNetJson.analysisSearchList(it) }
//                if (songList == null) {
//                    println("SearchListNetDataSource:44:NULL")
//                    onSuccess(listOf())
//                } else {
//                    onSuccess(songList)
//                }
//            }
//        })
//        val responseData = okHttpClient.newCall(request).execute().body()!!.string()
//        val songs = AnalysisNetJson.analysisSearchList(responseData)
//        DatabaseManager.db.searchSongDao.save(*songs.toTypedArray())
//        return songs
//
//    }




    suspend fun getListFromNet(word:String,limit:Int,offset:Int) = RequestNetWork.musicService.searchSong(word, limit, offset)

    fun getListFromLocal(fileName: String): Any? {
        var data: Any? = null
        when (fileName) {
            SONG_NAME_ELEMENT_DB -> data = DatabaseManager.db.searchSongDao.getSongName()
            SONG_ID_ELEMENT_DB -> data = DatabaseManager.db.searchSongDao.getSongId()
            ARTIST_ELEMENT_DB -> data = DatabaseManager.db.searchSongDao.getArtist()
            ALBUM_ELEMENT_DB -> data = DatabaseManager.db.searchSongDao.getAlbum()
            ALL_ELEMENT_DB -> data = DatabaseManager.db.searchSongDao.getAll()
        }
        return data
    }

    fun removeAllLocal() {
        DatabaseManager.db.searchSongDao.delete()
    }


}