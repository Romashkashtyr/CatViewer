package com.romashka.catviewer.domain

import androidx.lifecycle.LiveData
import com.romashka.catviewer.data.CatNetwork
import com.romashka.catviewer.data.CatsApi
import retrofit2.Call

class CatRepository(private val catsApi: CatsApi) {

    suspend fun getCatFact() : CatFactResponse {
        return catsApi.getFacts()
    }

}