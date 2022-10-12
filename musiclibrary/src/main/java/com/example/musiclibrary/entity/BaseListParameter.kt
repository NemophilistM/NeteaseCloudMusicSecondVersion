package com.example.musiclibrary.entity

data class BaseListParameter(
    private val songName: String,
    private val songPath: String,
    private val artist: List<Artist>,
    private val album: List<Album>
)
