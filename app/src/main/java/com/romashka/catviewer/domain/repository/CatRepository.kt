package com.romashka.catviewer.domain.repository

import com.romashka.catviewer.data.CatsApi
import com.romashka.catviewer.domain.model.CatData

class CatRepository(private val catsApi: CatsApi) {

    suspend fun getCatFact() : CatData {
        return catsApi.getFacts()
    }

}