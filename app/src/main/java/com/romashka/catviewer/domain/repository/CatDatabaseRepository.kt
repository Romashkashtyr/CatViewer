package com.romashka.catviewer.domain.repository

import androidx.lifecycle.LiveData
import com.romashka.catviewer.CatViewerApp
import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.room.AppDatabase
import com.romashka.catviewer.room.CatDataDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class CatDatabaseRepository()  {


    private val dbRepository = AppDatabase.instanse.catDao()

    //val allCatData: List<CatData> = dbRepository.getAll()

    suspend fun insert(catDataInfo: CatData){
        dbRepository.insertData(catDataInfo)
    }

    suspend fun delete(catDataInfo: CatData){
        dbRepository.delete(catDataInfo)
    }

    suspend fun getAllInfo(): List<CatData> {
        return dbRepository.getAll()
    }
}