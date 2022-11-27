package com.example.musiclibrary.repository


/**
 * 基础的存储仓库接口，每个存储仓库都会实现这个接口，包括获取接口，播放接口
 */
interface BaseRepository {
    /**
     * 获取接口
     */
    suspend fun getList() : List<Any>

    /**
     * 播放接口
     */
    fun play(position:Int)

}