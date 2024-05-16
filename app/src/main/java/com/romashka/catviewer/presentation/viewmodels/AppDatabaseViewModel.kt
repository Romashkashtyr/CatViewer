package com.romashka.catviewer.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.domain.repository.CatDatabaseRepository
import com.romashka.catviewer.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppDatabaseViewModel(app: Application): AndroidViewModel(app) {

    private val repository: CatDatabaseRepository
    private val allInfo: LiveData<List<CatData>>


    init {
        val dao = AppDatabase.getDatabase(app).catDao()
        repository = CatDatabaseRepository(dao)
        allInfo = repository.allCatData
    }

    fun deleteCat(catDataInfo: CatData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(catDataInfo)
        }
    }

    fun insertCat(catDataInfo: CatData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(catDataInfo)
        }
    }
}