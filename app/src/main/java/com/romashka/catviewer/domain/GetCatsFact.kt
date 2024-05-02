package com.romashka.catviewer.domain

import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.domain.repository.CatRepository

class GetCatsFact(private val repository: CatRepository)  {

    suspend fun execute() : CatData {
        return repository.getCatFact()
    }

}