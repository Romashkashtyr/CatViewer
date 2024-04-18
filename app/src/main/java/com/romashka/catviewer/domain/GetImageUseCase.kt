package com.romashka.catviewer.domain

class GetImageUseCase(private val repository: CatsRepositoryImage) {

    suspend fun invokeImage() : List<CatImage> {
        return repository.getCatImage()
    }
}