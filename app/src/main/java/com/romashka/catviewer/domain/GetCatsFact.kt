package com.romashka.catviewer.domain

import com.romashka.catviewer.data.CatNetwork
import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.domain.repository.CatRepository

class GetCatsFact {

    private val catsApi = CatNetwork.catFactApi
    private val repository =  CatRepository(catsApi)
    suspend fun execute() : CatFactResponse {
        return repository.getCatFact()
    }

}