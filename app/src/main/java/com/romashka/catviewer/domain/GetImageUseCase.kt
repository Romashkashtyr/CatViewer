package com.romashka.catviewer.domain

class GetImageUseCase(private val repository: CatsRepositoryImage) {

    suspend fun executeImage() : List<CatData> {
        return repository.getCatImage()
    }
}