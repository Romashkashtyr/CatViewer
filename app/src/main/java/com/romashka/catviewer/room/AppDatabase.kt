package com.romashka.catviewer.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.room.datainterfaces.CatDataForRoom

@Database(entities = [CatDataForRoom::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun catDao() : CatDataDao



    companion object{
        private var instanse: AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase{
            return instanse ?: synchronized(this){
                val catDb = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "catListing"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                instanse = catDb
                catDb
            }

        }

    }




}