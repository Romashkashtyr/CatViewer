package com.romashka.catviewer.domain.repository

import androidx.lifecycle.LiveData
import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.room.CatDataDao
import com.romashka.catviewer.room.datainterfaces.CatDataForRoom

class CatDatabaseRepository(private val dbRepository: CatDataDao) {

    val allCatData: LiveData<List<CatDataForRoom>> = dbRepository.getAll()

    suspend fun insert(catDataInfo: CatDataForRoom){
        dbRepository.insertAll(catDataInfo)
    }

    suspend fun delete(catDataInfo: CatDataForRoom){
        dbRepository.delete(catDataInfo)
    }
}