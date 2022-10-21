package com.example.musiclibrary.source

import com.example.musiclibrary.entity.BaseListParameter

/**
 * 数据仓库需要实现的基础代码
 */
interface BaseDatasource {
    fun getListFromNet(passWord:String = "",curPage:Int = 0) : List<BaseListParameter>
    fun getListFromLocal(fileName: String = "")
}