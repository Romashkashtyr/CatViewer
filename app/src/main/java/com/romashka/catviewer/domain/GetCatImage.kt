package com.romashka.catviewer.domain

import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.domain.repository.CatsRepositoryImage

class GetCatImage(private val repository: CatsRepositoryImage) {

    suspend fun executeImage() : List<CatImage> {
        return repository.getCatImage()
    }
}