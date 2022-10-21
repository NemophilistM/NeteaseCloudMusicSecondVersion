package com.example.musiclibrary.database

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.musiclibrary.NAME_DB

object DatabaseManager {
    private val MIGRATIONS = arrayOf(Migration1)
    private var application = Application()
    val db: MusicDatabase by lazy {
        Room.databaseBuilder(application.applicationContext, MusicDatabase::class.java, NAME_DB)
            .addMigrations(*MIGRATIONS)
            .build()
    }

    private object Migration1 : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // 数据库的升级语句
            // database.execSQL("")
        }
    }
}