package com.example.musiclibrary.source

import android.util.Log
import com.example.musiclibrary.*
import com.example.musiclibrary.entity.BaseListParameter
import com.example.musiclibrary.entity.HotKey
import com.example.musiclibrary.util.AnalysisNetJson
import kotlinx.coroutines.runBlocking
import okhttp3.*
import java.io.IOException

class HotKeyListNetDatasource {
    fun getListFromNet(): List<HotKey> {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url(BASE_URL + HOT_SONG_LIST)
            .build()
//            okHttpClient.newCall(request).enqueue(object : Callback {
//                override fun onFailure(call: Call, e: IOException) {
//                    e.printStackTrace()
//                    onFailure(e)
//                }
//                override fun onResponse(call: Call, response: Response) {
//                    val responseData = response.body()?.string()
//                    val hots = responseData?.let { AnalysisNetJson.analysisHotKey(it) }
//                    if(hots == null){
//                        println("HotKeyListNetDataSource:songList:NULL")
//                        onSuccess(listOf())
//                    }else{
//                        onSuccess(hots)
//                    }
//                }
//            })


        val responseData = okHttpClient.newCall(request).execute().body()!!.string()
        return AnalysisNetJson.analysisHotKey(responseData)

    }

    fun getListFromLocal(fileName: String) {
        TODO("Not yet implemented")
    }
}