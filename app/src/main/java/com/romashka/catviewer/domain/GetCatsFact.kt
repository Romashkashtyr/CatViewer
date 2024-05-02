package com.romashka.catviewer.domain

class GetCatsFact(private val repository: CatRepository)  {

    suspend fun execute() : CatData {
        return repository.getCatFact()
    }

}