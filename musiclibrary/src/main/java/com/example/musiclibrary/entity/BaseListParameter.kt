package com.example.musiclibrary.entity


// 每个列表基本的参数，各个列表都会实现这个接口
interface BaseListParameter {
    val songName: String
    val songId: Long
}
