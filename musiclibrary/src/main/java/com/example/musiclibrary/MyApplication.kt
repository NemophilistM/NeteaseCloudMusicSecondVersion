package com.example.musiclibrary

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

object MyApplication: Application() {
    private lateinit var  application:Application

    fun getApplication():Application{
        return application
    }
    fun setApplication(application: Application){
        this.application = application
    }

}