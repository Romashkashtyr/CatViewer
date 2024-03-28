package com.romashka.catviewer.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.romashka.catviewer.data.CatNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatsRepository: ViewModel() {

    val catFactsHistory = mutableListOf<CatFactResponse>()

    val catApi = CatNetwork.catFactApi

    val _catData = MutableLiveData<CatFactResponse>()
    val catData: LiveData<CatFactResponse>
        get() = _catData

    val _catFact = MutableLiveData<CatFactResponse>()
    val catFact: LiveData<CatFactResponse>
        get() = _catFact

    init {
        getCatsFactUseCase()
    }

    fun getCatsFactUseCase(): LiveData<CatFactResponse>{
        val call = catApi.getFacts()
        call.enqueue(object : Callback<CatFactResponse>{
            override fun onResponse(call: Call<CatFactResponse>, response: Response<CatFactResponse>) {
                if(response.isSuccessful)
                 _catData.value = response.body()
                val fact = response.body()
                if (fact != null) {
                    addToHistory(fact)
                }
            }

            override fun onFailure(call: Call<CatFactResponse>, response: Throwable) {
                throw RuntimeException("${response.message}")
            }

        })
        return _catData
    }

    fun addToHistory(factResponse: CatFactResponse){
        catFactsHistory.add(factResponse)

    }

    fun getPreviousFact(): CatFactResponse? {
       return if(catFactsHistory.size > 1){
            catFactsHistory[catFactsHistory.size - 2]
        } else {
            null
       }
    }

    fun onRefreshImage(){}

    fun onRefreshFact(newFact: CatFactResponse): String{
        _catFact.value = newFact
        return newFact.fact
    }
}