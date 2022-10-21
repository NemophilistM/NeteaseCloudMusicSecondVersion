package com.example.musiclibrary.source

import com.example.musiclibrary.BASE_URL
import com.example.musiclibrary.KEY_WORD_PASSWORD
import com.example.musiclibrary.OFFSET_PASSWORD
import com.example.musiclibrary.REQUEST_PAGE_MAX
import com.example.musiclibrary.entity.SearchSong
import com.example.musiclibrary.util.AnalysisNetJson
import okhttp3.*

class SearchListNetDatasource {
    fun getListFromNet(passWord: String, curPage: Int): List<SearchSong> {

        val okHttpClient = OkHttpClient()
        val requestBody = FormBody.Builder()
            .add(KEY_WORD_PASSWORD, passWord)
            .add(OFFSET_PASSWORD, ((curPage - 1) * REQUEST_PAGE_MAX).toString())
            .build()
        val request = Request.Builder()
            .url(BASE_URL)
            .post(requestBody)
            .build()

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
        val responseData = okHttpClient.newCall(request).execute().body()!!.string()
        return AnalysisNetJson.analysisSearchList(responseData)

    }

    fun getListFromLocal(fileName: String) {
        TODO("Not yet implemented")
    }
}