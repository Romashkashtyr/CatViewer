package com.romashka.catviewer.domain

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.romashka.catviewer.data.CatNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class CatsViewModel: ViewModel() {

    private var lastLoadedImageUrl : String? = null

    val catFactsHistory = mutableListOf<CatFactResponse>()

    val catApi = CatNetwork.catFactApi

    val _catData = MutableLiveData<CatFactResponse>()
    val catData: LiveData<CatFactResponse>
        get() = _catData

    val _catImage = MutableLiveData<CatImage>()
    val catImage: LiveData<CatImage>
        get() = _catImage

    var currentFactIndex = 0

//    private val _catHistory = MutableLiveData<MutableList<CatHistory>>()
//    val catHistory : LiveData<MutableList<CatHistory>>
//        get() = _catHistory


    val catImageApi = CatNetwork.catApiImage
    //val moshiCatAdapter = CatNetwork.catAdapterMoshi

    val catsItemsShow = mutableListOf<CatFactResponse>()

    init {
        getCatsFactUseCase()
    }

    fun getCatsFactUseCase(): LiveData<CatFactResponse> {
        val call = catApi.getFacts()
        call.enqueue(object : Callback<CatFactResponse> {
            override fun onResponse(
                call: Call<CatFactResponse>,
                response: Response<CatFactResponse>
            ) {
                if (response.isSuccessful)
                    _catData.value = response.body()
//                val fact = response.body()
//                if (fact != null) {
//                    addToHistory(fact)
//                }
            }

            override fun onFailure(call: Call<CatFactResponse>, response: Throwable) {
                throw RuntimeException("${response.message}")
            }

        })
        return _catData

    }


    private fun checkCatsFacts(fact : CatFactResponse) : CatFactResponse{
        catsItemsShow[currentFactIndex]
        currentFactIndex++
        if(catsItemsShow != null){
            catsItemsShow.add(fact)
        }
        return catsItemsShow[currentFactIndex]
        TODO()
    }



    fun loadRandomCatImage(context: Context, imageView: ImageView) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = catImageApi.getCatImage()
                val imageUrl = response.firstOrNull()?.url

                CoroutineScope(Dispatchers.Main).launch {
                    Glide.with(context)
                        .load(imageUrl)
                        .into(imageView)
                }

            } catch (e: IOException) {
                throw IOException("Isn't responding")
            } catch (e: HttpException){
                e.response()
                e.message
            }



        }






//        fun addToHistory(catHistoryItems: CatHistory){
//            val currentHistory = _catHistory.value ?: mutableListOf()
//            currentHistory.add(catHistoryItems)
//            _catHistory.value = currentHistory
//        }
//
//        fun getPreviousHistory() : CatHistory?{
//            val currentCatsHistory = _catHistory.value
//
//            return if(currentCatsHistory != null){
//                currentCatsHistory.last()
//            } else {
//                null
//            }
//        }

//    fun loadRandomCatImage(): LiveData<CatImage>  {
//        val call = catApi.getCatImage()
//        call.enqueue(object : Callback<CatImage> {
//            override fun onResponse(call: Call<CatImage>, response: Response<CatImage>) {
//                if (response.isSuccessful) {
//                    _catImage.value = response.body()
//                    }
//                }
//
//            override fun onFailure(p0: Call<CatImage>, p1: Throwable) {
//                throw RuntimeException("${p1.message}")
//            }
//        })
//        return _catImage
//
//    }




        fun getPreviousFact(): CatFactResponse? {
            return if (catFactsHistory.size > 1) {
                catFactsHistory[catFactsHistory.size - 2]
            } else {
                null
            }
        }


    }
    fun addToHistory(fact : LiveData<CatFactResponse>) : List<LiveData<CatFactResponse>> {
        val newFactsList = mutableListOf<LiveData<CatFactResponse>>()
        newFactsList.add(fact)
        return newFactsList
    }


    fun lastImage(context: Context, imageView: ImageView) : String?{
        val imageUrl = loadRandomCatImage(context, imageView)
        lastLoadedImageUrl = imageUrl.toString()
        return lastLoadedImageUrl
    }

    interface GetFunctionOfViewModel{

        fun getCatsFactsUseCase() : LiveData<CatFactResponse>
    }

}

//    fun onRefreshImage(){
//    }


//    fun onRefreshFact(newFact: CatFactResponse): String{
//        _catFact.value = newFact
//        return newFact.fact
//    }
