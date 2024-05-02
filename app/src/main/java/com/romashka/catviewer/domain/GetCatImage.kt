package com.romashka.catviewer.domain

class GetCatImage(private val repository: CatsRepositoryImage) {

    suspend fun executeImage() : List<CatData> {
        return repository.getCatImage()
    }
}