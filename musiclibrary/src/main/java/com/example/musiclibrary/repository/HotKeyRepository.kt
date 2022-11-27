package com.example.musiclibrary.repository

import androidx.lifecycle.liveData
import com.example.musiclibrary.entity.HotKeyJson
import com.example.musiclibrary.source.HotKeyListNetDatasource
import kotlinx.coroutines.Dispatchers

object HotKeyRepository {

    private val datasource = HotKeyListNetDatasource()
//        借用livedata的封装进行的网络申请
     fun getList() = liveData(Dispatchers.IO){
        val result =try {
            val response = datasource.getListFromNet()
            if (response.code == 200){
                val data = response.data

                // 将获取的数据存入数据库，这部分· 先写了，但是不实现，
                // 因为还需要增加从本地获取的，只要当本地获取不到的时候才从网络获取并进行保存
//                if (data != null) {
//                    val list : MutableList<HotKey> = mutableListOf()
//                    for (i in data.indices) {
//                        list.add(HotKey(data[i].searchWord!!,data[i].score!!,data[i].content!!,id = i))
//                    }
//                    DatabaseManager.db.hotKeyDao.save(*list.toTypedArray())
//                }
                Result.success(data)
            }else{
                Result.failure(RuntimeException(""))
            }
        }catch (e:Exception){
            println(e.message)
            Result.failure(e)

        }
        emit(result)
    }


}