package com.romashka.catviewer.domain.repository

import com.romashka.catviewer.data.CatsApi
import com.romashka.catviewer.domain.CatImage
import com.romashka.catviewer.domain.model.CatData

class CatsRepositoryImage(private val catsImageApi : CatsApi) {

    suspend fun getCatImage() : List<CatImage> {
        return catsImageApi.getCatImage()
    }
}