package com.romashka.catviewer

import android.app.Application
import com.romashka.catviewer.room.AppDatabase

class CatViewerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabase.initDatabase(applicationContext)
    }
}