package com.romashka.catviewer.domain

import com.romashka.catviewer.data.CatsApi

class CatsRepositoryImage(private val catsImageApi : CatsApi) {

    suspend fun getCatImage() : List<CatData> {
        return catsImageApi.getCatImage()
    }
}