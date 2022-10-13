package com.example.musiclibrary.repository

import com.example.musiclibrary.entity.BaseListParameter

class PlayerListRepository : BaseRepository {
    override fun getList() : List<BaseListParameter>{
        TODO()
    }

    override fun play(position: Int) {
        TODO("Not yet implemented")
    }

    // 用于整体更新播放列表
    fun upDataList( newList: List<BaseListParameter>){
        TODO("Not yet implemented")
    }

    // 单一删除某一元素的接口
    fun deleteElement(){
        TODO("Not yet implemented")
    }

    // 下载接口
    fun downloadElement(){
        TODO("Not yet implemented")
    }


    //添加歌曲接口
    fun addElement(){
        TODO("Not yet implemented")
    }

}