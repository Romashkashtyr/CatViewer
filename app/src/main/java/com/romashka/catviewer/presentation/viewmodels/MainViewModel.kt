package com.romashka.catviewer.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romashka.catviewer.domain.CatFactResponse
import com.romashka.catviewer.domain.CatImage
import com.romashka.catviewer.domain.GetCatImage
import com.romashka.catviewer.domain.GetCatsFact
import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.domain.repository.CatDatabaseRepository
import com.romashka.catviewer.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import java.lang.Exception

class MainViewModel(app: Application, private val getCatsFact: GetCatsFact, private val getCatImage: GetCatImage)
 : AndroidViewModel(app) {

    private val repository: CatDatabaseRepository
    private var allInfo: LiveData<List<CatData>>



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
        nextClickPage()
        val dao = AppDatabase.getDatabase(app).catDao()
        repository = CatDatabaseRepository(dao)
        allInfo = repository.allCatData
    }


    private suspend fun loadingTheWholeCatDataToHistory(){
        val image = getCatImage.executeImage().firstOrNull()
        val fact = getCatsFact.execute().fact
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
            //    loadingRandomCatImage()
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
                _catData.postValue(getCatsFact.execute())
            } catch (e: IOException) {
                throw IOException("Isn't responding")
            } catch (e: HttpException) {
                e.response()
                e.message
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val catImageIt = getCatImage.executeImage().firstOrNull()
                catImageIt?.let {image ->
                    _catImageInfo.postValue(image)
                } ?: throw IOException("No cat image found")
            } catch (e: HttpException) {
                e.response()
                e.message
            }
        }
    }

    fun saveData(catDataInfo: CatData){
        try {
            CoroutineScope(Dispatchers.IO).launch {
                repository.insert(catDataInfo)
            }
        } catch (e: Exception) {
            e.message
        }
    }

    fun deleteData(catDataInfo: CatData) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                repository.delete(catDataInfo)
            }
        } catch (e: Exception){
            e.message
        }
    }



}


