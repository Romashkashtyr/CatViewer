package com.romashka.catviewer.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.romashka.catviewer.domain.GetCatImage
import com.romashka.catviewer.domain.GetCatsFact
import com.romashka.catviewer.presentation.MainActivity

class MainViewModelFactory(private val getCatFact: GetCatsFact, private val getCatImage: GetCatImage, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress()
            return MainViewModel(application,getCatFact, getCatImage) as T
        }
        throw IllegalArgumentException("Unknown viewModel")
    }
}
