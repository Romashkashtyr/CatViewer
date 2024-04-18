package com.romashka.catviewer.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.romashka.catviewer.data.CatNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class MainViewModel
//    private val catsFactGetting: GetCatsFactUseCase,
//    private val catsImageGetting: GetImageUseCase
 : ViewModel() {


    private val catsFactGetting = GetCatsFactUseCase(CatRepository(CatNetwork.catFactApi))
    private val catsImageGetting = GetImageUseCase(CatsRepositoryImage(CatNetwork.catApiImage))
    private var lastLoadedImageUrl: String? = null

    val catFactsHistory = mutableListOf<CatFactResponse>()

    val catApi = CatNetwork.catFactApi

    val _catData = MutableLiveData<CatFactResponse>()
    val catData: LiveData<CatFactResponse>
        get() = _catData

    val _catImage = MutableLiveData<List<CatImage>>()
    val catImage: LiveData<List<CatImage>>
        get() = _catImage

    var currentFactIndex = 0

//    private val _catHistory = MutableLiveData<MutableList<CatHistory>>()
//    val catHistory : LiveData<MutableList<CatHistory>>
//        get() = _catHistory


    val catImageApi = CatNetwork.catApiImage
    //val moshiCatAdapter = CatNetwork.catAdapterMoshi

    val catsItemsShow = mutableListOf<CatFactResponse>()

    init {
        //  getCatsFactUseCase()  -> actual
    }

    init {
        getCattingFact()
        loadingRandomCatImage()
    }


    fun getCattingFact() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _catData.postValue(catsFactGetting.invoke())
            } catch (e: IOException) {
                throw IOException("Isn't responding")
            } catch (e: HttpException) {
                e.response()
                e.message
            }
        }
    }

    fun loadingRandomCatImage() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val catImageIt = catsImageGetting.invokeImage().firstOrNull()
                catImageIt?.let {
                    _catImage.postValue(listOf(it))
                } ?: throw IOException("No cat image found")
               // _catImage.postValue(catsImageGetting.invokeImage())
            } catch (e: IOException) {
                throw IOException("Isn't responding")
            } catch (e: HttpException) {
                e.response()
                e.message
            }
        }


//    fun getCatsFactUseCase(): LiveData<CatFactResponse> {  -> actual
//        getCatFacts.getCatsFactUseCase()
//        val call = catApi.getFacts()
//        call.enqueue(object : Callback<CatFactResponse> {
//            override fun onResponse(
//                call: Call<CatFactResponse>,
//                response: Response<CatFactResponse>
//            ) {
//                if (response.isSuccessful)
//                    _catData.value = response.body()
////                val fact = response.body()
////                if (fact != null) {
////                    addToHistory(fact)
////                }
//            }
//
//            override fun onFailure(call: Call<CatFactResponse>, response: Throwable) {
//                throw RuntimeException("${response.message}")
//            }
//
//        })
//        return _catData
//
//    }


        fun checkCatsFacts(fact: CatFactResponse): CatFactResponse {
            catsItemsShow[currentFactIndex]
            currentFactIndex++
            if (catsItemsShow != null) {
                catsItemsShow.add(fact)
            }
            return catsItemsShow[currentFactIndex]
            TODO()
        }


//    fun loadRandomCatImage(context: Context, imageView: ImageView) { -> actual
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val response = catImageApi.getCatImage()
//                val imageUrl = response.firstOrNull()?.url
//
//                CoroutineScope(Dispatchers.Main).launch {
//                    Glide.with(context)
//                        .load(imageUrl)
//                        .into(imageView)
//                }
//
//            } catch (e: IOException) {
//                throw IOException("Isn't responding")
//            } catch (e: HttpException){
//                e.response()
//                e.message
//            }
//        }


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

    fun addToHistory(fact: LiveData<CatFactResponse>): List<LiveData<CatFactResponse>> {
        val newFactsList = mutableListOf<LiveData<CatFactResponse>>()
        newFactsList.add(fact)
        return newFactsList
    }


//    fun lastImage(context: Context, imageView: ImageView) : String?{ -> actual
//        val imageUrl = loadRandomCatImage(context, imageView)
//        lastLoadedImageUrl = imageUrl.toString()
//        return lastLoadedImageUrl
//    }

    interface GetFunctionOfViewModel {

        fun getCatsFactsUseCase(): LiveData<CatFactResponse>
    }




}

//    fun onRefreshImage(){
//    }


//    fun onRefreshFact(newFact: CatFactResponse): String{
//        _catFact.value = newFact
//        return newFact.fact
//    }
