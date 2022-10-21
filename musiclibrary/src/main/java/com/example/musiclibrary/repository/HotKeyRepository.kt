package com.example.musiclibrary.repository

import com.example.musiclibrary.database.DatabaseManager
import com.example.musiclibrary.entity.BaseListParameter
import com.example.musiclibrary.entity.HotKey
import com.example.musiclibrary.source.HotKeyListNetDatasource
import kotlinx.coroutines.*

class HotKeyRepository {
    /**
     * 没有捕获网络异常，将异常上抛给ui层捕获之后展示
     */
    suspend fun getList(): List<HotKey> {
        //调用数据源类获取网络数据
        return withContext(Dispatchers.IO) {
            val datasource = HotKeyListNetDatasource()
            val hots = datasource.getListFromNet()
            DatabaseManager.db.hotKeyDao.save(*hots.toTypedArray())
            hots
        }

    }

}