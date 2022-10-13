package com.example.musiclibrary.source

/**
 * 数据仓库需要实现的基础代码
 */
interface BaseDatasource {
    fun getListFromNet(passWord:String = "",curPage:Int = 0)
    fun getListFromLocal(fileName: String = "")
}