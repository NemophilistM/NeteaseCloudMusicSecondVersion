package com.example.musiclibrary.source

import android.util.Log
import com.example.musiclibrary.*
import com.example.musiclibrary.entity.HotKey
import com.example.musiclibrary.network.RequestNetWork
import com.example.musiclibrary.util.AnalysisNetJson
import kotlinx.coroutines.runBlocking
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.io.IOException
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class HotKeyListNetDatasource {
//        val okHttpClient = OkHttpClient()
//        val request = Request.Builder()
//            .url(BASE_URL + HOT_SONG_LIST)
//            .build()
////            okHttpClient.newCall(request).enqueue(object : Callback {
////                override fun onFailure(call: Call, e: IOException) {
////                    e.printStackTrace()
////                    onFailure(e)
////                }
////                override fun onResponse(call: Call, response: Response) {
////                    val responseData = response.body()?.string()
////                    val hots = responseData?.let { AnalysisNetJson.analysisHotKey(it) }
////                    if(hots == null){
////                        println("HotKeyListNetDataSource:songList:NULL")
////                        onSuccess(listOf())
////                    }else{
////                        onSuccess(hots)
////                    }
////                }
////            })
//
//
//        val responseData = okHttpClient.newCall(request).execute().body()!!.string()
//        return AnalysisNetJson.analysisHotKey(responseData)


//        suspend fun getListFromNet() = RequestNetWork.musicService.hotKey().await()
//        private suspend fun <T> Call<T>.await(): T {
//            return suspendCoroutine { continuation ->
//                enqueue(object : Callback<T> {
//                    override fun onResponse(call: Call<T>, response: Response<T>) {
//                        val body = response.body()
//                        if (body != null) continuation.resume(body)
//                        else continuation.resumeWithException(RuntimeException("response body is null"))
//                    }
//
//                    override fun onFailure(call: Call<T>, t: Throwable) {
//                        continuation.resumeWithException(t)
//                    }
//                })
//            }
//        }

    suspend fun getListFromNet() = RequestNetWork.musicService.hotKey()

    fun getListFromLocal(fileName: String) {
        TODO("Not yet implemented")
    }
}