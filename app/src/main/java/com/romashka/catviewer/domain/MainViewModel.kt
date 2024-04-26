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
 : ViewModel() {

    private val catsFactGetting = GetCatsFactUseCase(CatRepository(CatNetwork.catFactApi))
    private val catsImageGetting = GetImageUseCase(CatsRepositoryImage(CatNetwork.catApiImage))
    private var lastLoadedImageUrl: String? = null


    private val shownFact = mutableListOf<CatFactResponse>()
    private val shownImage = mutableListOf<CatImage>()
    private val catHistoryList = mutableListOf<CatData>()

    val catFactsHistory = mutableListOf<CatFactResponse>()

    val _catDataHistory = MutableLiveData<CatData?>()
    val catDataHistory: LiveData<CatData?>
        get() = _catDataHistory


    val _catData = MutableLiveData<CatFactResponse>()
    val catData: LiveData<CatFactResponse>
        get() = _catData

    val _catImage = MutableLiveData<List<CatImage>>()
    val catImage: LiveData<List<CatImage>>
        get() = _catImage

    var currentFactIndex = 0

    val catsItemsShow = mutableListOf<CatFactResponse>()

    var catIndex = 0

    init {
        getCattingFact()
        loadingRandomCatImage()
    }

    fun moveToNextPage(){
        if (catHistoryList.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                val newFact = catsFactGetting.execute().fact
                val newImage = catsImageGetting.executeImage().firstOrNull()
                if (newFact != null && newImage != null) {
                    val catDataItems = CatData(fact = newFact, catsImageUrl = newImage)
                    addToHistoryList(catDataItems)
                    updateCurrentFact(newFact)
                    updateCurrentImage(newImage)
                }
            }
        }
    }

    private fun updateCurrentFact(newFact : String){
        val currentData = catHistoryList.lastOrNull()
        currentData?.fact = newFact
        _catDataHistory.postValue(currentData)
    }

    private fun updateCurrentImage(newImage : CatImage){
        val currentImageData = catHistoryList.lastOrNull()
        currentImageData?.catsImageUrl = newImage
        _catDataHistory.postValue(currentImageData)
    }


    private fun addToHistoryList(newData: CatData) {
        catHistoryList.add(newData)
        _catDataHistory.postValue(newData)
    }


    private fun getCattingFact() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _catData.postValue(catsFactGetting.execute())
            } catch (e: IOException) {
                throw IOException("Isn't responding")
            } catch (e: HttpException) {
                e.response()
                e.message
            }
        }
    }

    private fun loadingRandomCatImage() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val catImageIt = catsImageGetting.executeImage().firstOrNull()
                catImageIt?.let {
                    _catImage.postValue(listOf(it))
                } ?: throw IOException("No cat image found")
            } catch (e: HttpException) {
                e.response()
                e.message
            }
        }



        fun checkCatsFacts(fact: CatFactResponse): CatFactResponse {
            catsItemsShow[currentFactIndex]
            currentFactIndex++
            if (catsItemsShow != null) {
                catsItemsShow.add(fact)
            }
            return catsItemsShow[currentFactIndex]
            TODO()


        }

        fun addToHistory(fact: LiveData<CatFactResponse>): List<LiveData<CatFactResponse>> {
            val newFactsList = mutableListOf<LiveData<CatFactResponse>>()
            newFactsList.add(fact)
            return newFactsList
        }
    }
}


