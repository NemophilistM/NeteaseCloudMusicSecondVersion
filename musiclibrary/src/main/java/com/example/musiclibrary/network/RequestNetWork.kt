package com.example.musiclibrary.network

import com.example.musiclibrary.source.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object RequestNetWork {
    val musicService = ServiceCreator.create(MusicService::class.java)
}