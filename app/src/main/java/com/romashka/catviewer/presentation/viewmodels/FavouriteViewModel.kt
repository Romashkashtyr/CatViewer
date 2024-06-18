package com.romashka.catviewer.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.domain.repository.CatDatabaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteViewModel : ViewModel() {

    val favouriteListSavedCatData: MutableLiveData<List<CatData>> = MutableLiveData()
    private val repository = CatDatabaseRepository()

    fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            favouriteListSavedCatData.postValue(repository.getAllInfo())
        }
    }

    fun deleteCatData(data: CatData) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.delete(data)
        }

    }


}