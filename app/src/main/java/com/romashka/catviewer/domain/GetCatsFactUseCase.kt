package com.romashka.catviewer.domain

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.LiveData

class GetCatsFactUseCase(private val repository: CatsViewModel) {

    fun getCatsFactUseCase(): LiveData<CatFactResponse> {
        return repository.getCatsFactUseCase()
    }

    fun getCatsImageUseCase(context: Context, imageView: ImageView){
        repository.loadRandomCatImage(context, imageView)
    }
}