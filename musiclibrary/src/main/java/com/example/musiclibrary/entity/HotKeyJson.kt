package com.example.musiclibrary.entity

import com.example.musiclibrary.entity.HotKeyJson.DataDTO

class HotKeyJson {
    var code: Int? = null
    var data: List<DataDTO>? = null
    var message: String? = null

    class DataDTO {
        var searchWord: String? = null
        var score: Int? = null
        var content: String? = null
        var source: Int? = null
        var iconType: Int? = null
        var iconUrl: String? = null
        var url: String? = null
        var alg: String? = null
    }
}