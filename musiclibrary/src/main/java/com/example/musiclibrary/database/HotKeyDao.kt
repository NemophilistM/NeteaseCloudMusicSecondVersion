package com.example.musiclibrary.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.musiclibrary.entity.HotKey

@Dao
interface HotKeyDao {
    @Insert
    fun save(vararg data: HotKey)

    @Query("select searchWord from HotKey order by id ")
    fun getSearchWord(): LiveData<List<String>>

    @Query("select content from HotKey order by id ")
    fun getContent(): LiveData<List<String>>

    @Query("select score from HotKey order by id")
    fun getScore(): LiveData<List<Long>>

    @Query("select * from HotKey order by id")
    fun getAll(): LiveData<List<HotKey>>

    @Delete
    fun delete(vararg hotKey: HotKey)
}