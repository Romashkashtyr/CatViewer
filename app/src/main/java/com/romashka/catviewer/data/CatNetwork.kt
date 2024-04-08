package com.romashka.catviewer.data

import com.romashka.catviewer.domain.CatImage
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
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

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/v1/images/search/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val catApiImage = retrofit.create(CatsApi::class.java)




    //val catAdapterMoshi : JsonAdapter<CatImage> = moshi.adapter(CatImage::class.java)
}


