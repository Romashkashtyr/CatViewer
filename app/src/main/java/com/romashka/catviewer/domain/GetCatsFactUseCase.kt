package com.romashka.catviewer.domain

class GetCatsFactUseCase(private val repository: CatRepository)  {

    suspend fun execute() : CatFactResponse {
        return repository.getCatFact()
    }

}