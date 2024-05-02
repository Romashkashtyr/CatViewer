package com.romashka.catviewer.data

import com.romashka.catviewer.domain.CatData
import retrofit2.http.GET

interface CatsApi {

    @GET("/fact")
    suspend fun getFacts(): CatData

    @GET("v1/images/search")
    suspend fun getCatImage(): List<CatData>
}
