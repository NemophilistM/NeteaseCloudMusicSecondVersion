package com.example.musiclibrary.entity

import java.io.Serializable

data class Artist(
    private val id: Long,
    private val name: String,
    private val alias: String,
)
