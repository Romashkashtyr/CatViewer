package com.romashka.catviewer.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.domain.repository.CatDatabaseRepository
import com.romashka.catviewer.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppDatabaseViewModel(app: Application): AndroidViewModel(app) {

    private val repository: CatDatabaseRepository
    val allInfo: LiveData<List<CatData>>


    init {
        val dao = AppDatabase.getDatabase(app).catDao()
        repository = CatDatabaseRepository(dao)
        allInfo = repository.allCatData
    }

    fun deleteCat(catDataInfo: CatData){
        CoroutineScope(Dispatchers.IO).launch {
            repository.delete(catDataInfo)
        }
    }

    fun insertCat(catDataInfo: CatData){
        CoroutineScope(Dispatchers.IO).launch {
            repository.insert(catDataInfo)
        }
    }

//    fun getAllData(): LiveData<List<CatData>>{
//        CoroutineScope(Dispatchers.IO).launch {
//            repository.getAllInfo()
//        }
//        return
//    }
}