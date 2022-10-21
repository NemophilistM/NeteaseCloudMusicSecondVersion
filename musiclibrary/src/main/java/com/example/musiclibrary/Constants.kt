package com.example.musiclibrary

// 网络地址
const val BASE_URL:String = "http://106.15.2.32:3000/"
const val SEARCH_URL:String = "cloudsearch"
const val HOT_SONG_LIST = "search/hot/detail"

const val KEY_WORD_PASSWORD = "keywords"
const val OFFSET_PASSWORD = "offset"
const val REQUEST_PAGE_MAX = 20

//json解析用词
const val RESULT_JSON = "result"
const val HOTS_JSON = "hots"
const val HOTS_DETAIL_DATA_JSON = "data"
const val HOTS_DETAIL_DATA_SEARCH_WORD_JSON = "searchWord"
const val HOTS_DETAIL_DATA_SCORE_JSON = "score"
const val HOTS_DETAIL_DATA_CONTENT_JSON = "content"
const val FIRST_JSON = "first"
const val CODE_JSON = "code"
const val SONG_JSON = "songs"
const val ID_JSON = "id"
const val NAME_JSON = "name"
const val IMG_PATH_JSON = "img1v1Url"
const val ALBUM_JSON = "al"
const val ARTIST_JSON = "ar"
const val ALIA_JSON = "alia"
const val ALIAS_JSON = "alias"
const val PIC_URL_JSON = "picUrl"
const val MESSAGE_JSON = "message"
const val SUCCESS_CODE_JSON = 200



// 数据库常量池
const val  NAME_DB = "musicData.db"
const val  SEARCH_TABLE_NAME_DB = "SearchSong"
const val  HOT_KEY_TABLE_NAME_DB = "HotKey"
