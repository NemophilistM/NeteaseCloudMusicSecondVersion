package com.example.musiclibrary.network

import android.provider.SyncStateContract.Constants
import com.example.musiclibrary.entity.HotKey
import com.example.musiclibrary.entity.HotKeyJson
import com.example.musiclibrary.entity.SearchSongJson
import com.example.musiclibrary.entity.UrlJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicService {
    @GET("search/hot/detail")
    suspend fun hotKey(): HotKeyJson

    @GET("/cloudsearch")
    suspend fun searchSong(@Query("keywords") word:String,@Query("limit") limit:Int,@Query("offset")offset:Int ): SearchSongJson

    @GET("/song/url")
    suspend fun getMusicUrl(@Query("id")id:Int):UrlJson

}