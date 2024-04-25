package com.romashka.catviewer.domain

class GetImageUseCase(private val repository: CatsRepositoryImage) {

    suspend fun executeImage() : List<CatImage> {
        return repository.getCatImage()
    }
}