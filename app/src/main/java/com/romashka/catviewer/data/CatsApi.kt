package com.romashka.catviewer.data

import com.romashka.catviewer.domain.CatFactResponse
import retrofit2.Call
import retrofit2.http.GET

interface CatsApi {

    @GET("/fact")
    fun getFacts(): Call<CatFactResponse>
}