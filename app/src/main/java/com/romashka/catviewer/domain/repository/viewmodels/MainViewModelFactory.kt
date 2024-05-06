package com.romashka.catviewer.domain.repository.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.romashka.catviewer.domain.GetCatImage
import com.romashka.catviewer.domain.GetCatsFact

class MainViewModelFactory(val getCatFact: GetCatsFact, val getCatImage: GetCatImage) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress()
            return MainViewModel(getCatFact, getCatImage) as T
        }
        throw IllegalArgumentException("Unknown viewModel")
    }
}
