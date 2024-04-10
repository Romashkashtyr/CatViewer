package com.romashka.catviewer.data

import com.romashka.catviewer.domain.CatFactResponse
import com.romashka.catviewer.domain.CatImage
import retrofit2.Call
import retrofit2.http.GET

interface CatsApi {

    @GET("/fact")
    fun getFacts(): Call<CatFactResponse>

    @GET("v1/images/search")
    suspend fun getCatImage(): List<CatImage>
}


//v1/images/search