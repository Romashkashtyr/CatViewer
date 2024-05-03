package com.romashka.catviewer.domain.repository.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.romashka.catviewer.data.CatNetwork
import com.romashka.catviewer.domain.CatFactResponse
import com.romashka.catviewer.domain.CatImage
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

    val catHistoryList = mutableListOf<CatData>()

    val _catDataHistory = MutableLiveData<CatData?>()
    val catDataHistory: LiveData<CatData?>
        get() = _catDataHistory


    val _catData = MutableLiveData<CatFactResponse>()
    val catData: LiveData<CatFactResponse>
        get() = _catData

    val _catImageInfo = MutableLiveData<CatImage>()
    val catImageInfo: LiveData<CatImage>
        get() = _catImageInfo


    var catIndex = -1

    init {
        getCattingFact()
        loadingRandomCatImage()
    }

//    fun moveToNextPage(){
//        if (catHistoryList.isNotEmpty()) {
//            CoroutineScope(Dispatchers.IO).launch {
//                val newFact = catsFactGetting.execute().fact
//                val newImage = catsImageGetting.executeImage().firstOrNull()?.url
//                if (newFact != null && newImage != null) {
//                    val catDataItems = CatData(fact = newFact, url = newImage)
//                    addToHistoryList(catDataItems)
//                    updateCurrentFact(newFact)
//                    updateCurrentImage(newImage)
//                }
//            }
//        }
//    }

    private suspend fun loadingTheWholeCatDataToHistory(){
        val image = catsImageGetting.executeImage().firstOrNull()
        val fact = catsFactGetting.execute().fact
        catHistoryList.add(CatData(fact = fact, url = image?.url ?: ""))
    }

    fun nextClickPage(){
        CoroutineScope(Dispatchers.IO).launch {
            catIndex++
            if(catHistoryList.size >= catIndex){
                loadingTheWholeCatDataToHistory()
                updateFactAndImage()
            } else {
                getCattingFact()
                loadingRandomCatImage()
            }
        }
    }

    fun goToThePreviousPage(){
        CoroutineScope(Dispatchers.IO).launch {
            if(catIndex > 0 && catHistoryList.size >= catIndex){
                catHistoryList[catIndex--]
                updateFactAndImage()
            }
        }
    }

    private fun updateFactAndImage(){
        val updatedFact = catHistoryList[catIndex].fact
        val updatedImage = catHistoryList[catIndex].url
        _catDataHistory.postValue(CatData(fact = updatedFact, url = updatedImage))
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
                    _catImageInfo.postValue(image)
                } ?: throw IOException("No cat image found")
            } catch (e: HttpException) {
                e.response()
                e.message
            }
        }

    }


}


