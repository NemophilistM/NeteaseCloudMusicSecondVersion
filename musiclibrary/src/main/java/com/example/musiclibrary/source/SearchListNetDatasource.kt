package com.example.musiclibrary.source

import android.content.ContentProviderOperation.newCall
import com.example.musiclibrary.BASE_URL
import com.example.musiclibrary.KEY_WORD_PASSWORD
import com.example.musiclibrary.OFFSET_PASSWORD
import com.example.musiclibrary.REQUEST_PAGE_MAX
import kotlinx.coroutines.runBlocking
import okhttp3.*
import java.io.IOException
import java.text.Normalizer

object SearchListNetDatasource : BaseDatasource {
     override fun getListFromNet(passWord:String,curPage:Int){
        runBlocking {
            val okHttpClient = OkHttpClient()
            val requestBody = FormBody.Builder()
                .add(KEY_WORD_PASSWORD,passWord)
                .add(OFFSET_PASSWORD, ((curPage - 1) *REQUEST_PAGE_MAX).toString())
                .build()
            val request  = Request.Builder()
                .url(BASE_URL)
                .post(requestBody)
                .build()

            okHttpClient.newCall(request).enqueue(object :Callback{
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {

                }
            })
        }
    }

    override fun getListFromLocal(fileName: String) {
        TODO("Not yet implemented")
    }
}