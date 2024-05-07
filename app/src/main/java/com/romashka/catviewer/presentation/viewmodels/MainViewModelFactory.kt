package com.romashka.catviewer.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.romashka.catviewer.domain.GetCatImage
import com.romashka.catviewer.domain.GetCatsFact

class MainViewModelFactory(private val getCatFact: GetCatsFact, private val getCatImage: GetCatImage) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress()
            return MainViewModel(getCatFact, getCatImage) as T
        }
        throw IllegalArgumentException("Unknown viewModel")
    }
}
