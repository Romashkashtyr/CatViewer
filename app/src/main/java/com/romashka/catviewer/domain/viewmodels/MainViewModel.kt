package com.romashka.catviewer.domain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.romashka.catviewer.data.CatNetwork
import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.domain.repository.CatRepository
import com.romashka.catviewer.domain.repository.CatsRepositoryImage
import com.romashka.catviewer.domain.GetCatImage
import com.romashka.catviewer.domain.GetCatsFact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class MainViewModel
 : ViewModel() {

    private val catsFactGetting = GetCatsFact(CatRepository(CatNetwork.catFactApi))
    private val catsImageGetting = GetCatImage(CatsRepositoryImage(CatNetwork.catApiImage))

    private val catHistoryList = mutableListOf<CatData>()

    val _catDataHistory = MutableLiveData<CatData?>()
    val catDataHistory: LiveData<CatData?>
        get() = _catDataHistory


    val _catData = MutableLiveData<CatData>()
    val catData: LiveData<CatData>
        get() = _catData


    var currentFactIndex = 0

    val catsItemsShow = mutableListOf<CatData>()

    var catIndex = 0

    init {
        getCattingFact()
        loadingRandomCatImage()
    }

    fun moveToNextPage(){
        if (catHistoryList.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                val newFact = catsFactGetting.execute().fact
                val newImage = catsImageGetting.executeImage().firstOrNull()?.url
                if (newFact != null && newImage != null) {
                    val catDataItems = CatData(fact = newFact, url = newImage)
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

    private fun updateCurrentImage(newImage : String){
        val currentImageData = catHistoryList.lastOrNull()
        currentImageData?.url = newImage
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
                catImageIt?.let {image ->
                    _catData.postValue(image)
                } ?: throw IOException("No cat image found")
            } catch (e: HttpException) {
                e.response()
                e.message
            }
        }

    }


}


