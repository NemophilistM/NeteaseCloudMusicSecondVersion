package com.example.musiclibrary.entity


// 搜索列表的参数数据类
data class SearchList(
    override val songName: String,
    override val songId: Long,
    val artist: List<Artist>,
    val album: List<Album>
):BaseListParameter
