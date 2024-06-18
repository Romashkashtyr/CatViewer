package com.romashka.catviewer.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.romashka.catviewer.domain.model.CatData

@Database(entities = [CatData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun catDao() : CatDataDao


    companion object{
        lateinit var instanse: AppDatabase

        fun initDatabase(context: Context) {
            instanse = Room.databaseBuilder(context, AppDatabase::class.java, "catDb").build()

        }

    }




}