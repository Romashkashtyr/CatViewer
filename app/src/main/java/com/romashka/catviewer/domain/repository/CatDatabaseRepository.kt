package com.romashka.catviewer.domain.repository

import androidx.lifecycle.LiveData
import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.room.CatDataDao

class CatDatabaseRepository(private val dbRepository: CatDataDao) {

    val allCatData: LiveData<List<CatData>> = dbRepository.getAll()

    suspend fun insert(catDataInfo: CatData){
        dbRepository.insertAll(catDataInfo)
    }

    suspend fun delete(catDataInfo: CatData){
        dbRepository.delete(catDataInfo)
    }
}