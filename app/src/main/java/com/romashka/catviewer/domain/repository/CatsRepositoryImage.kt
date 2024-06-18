package com.romashka.catviewer.domain.repository

import com.romashka.catviewer.data.CatNetwork
import com.romashka.catviewer.data.CatsApi
import com.romashka.catviewer.domain.CatImage
import com.romashka.catviewer.domain.model.CatData

class CatsRepositoryImage {

    private val catsApi = CatNetwork.catApiImage
    suspend fun getCatImage() : List<CatImage> {
        return catsApi.getCatImage()
    }
}