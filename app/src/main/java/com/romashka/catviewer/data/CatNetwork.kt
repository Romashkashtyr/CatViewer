package com.romashka.catviewer.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object CatNetwork {

    private const val BASE_URL = "https://catfact.ninja/fact/"

    private fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val catFactApi: CatsApi = getInstance().create(CatsApi::class.java)
}