package com.romashka.catviewer.domain

class GetCatsFactUseCase(private val repository: CatRepository)  {

    suspend fun execute() : CatData {
        return repository.getCatFact()
    }

}