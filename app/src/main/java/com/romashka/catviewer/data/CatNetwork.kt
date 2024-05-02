package com.romashka.catviewer.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object CatNetwork {

    private const val BASE_URL = "https://catfact.ninja/fact/"


    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val catFactApi: CatsApi = getInstance().create(CatsApi::class.java)



    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val catApiImage = retrofit.create(CatsApi::class.java)

}


//https://api.thecatapi.com/v1/images/search/

