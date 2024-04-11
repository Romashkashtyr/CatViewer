package com.romashka.catviewer.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CatHistoryViewModel : ViewModel(){


    private val functionsOfViewModel = CatsViewModel()

    var currentIndex = -1

    private val _catHistory = MutableLiveData<MutableList<CatHistory>>()
    val catHistory : LiveData<MutableList<CatHistory>>
        get() = _catHistory

    init {
        _catHistory.value = mutableListOf()
    }

//    fun addToHistory(catHistoryItems: CatHistory){
//        val currentHistory = _catHistory.value ?: mutableListOf()
//        currentHistory.add(catHistoryItems)
//        _catHistory.value = currentHistory
//    }
//    fun addToHistory(fact : String , imageUrl : CatImage){ actual
//        val newFact = CatHistory(fact, imageUrl)
//        _catHistory.value?.add(newFact)
//        _catHistory.value = _catHistory.value
//    }




    fun addToHistory(catHistory: CatHistory){
        val history = _catHistory.value ?: mutableListOf()
        history.add(catHistory)
        _catHistory.value = history
        currentIndex = history.size - 1
        TODO()
    }

    fun getCurrentFact() : CatHistory?{
        return  _catHistory.value?.get(currentIndex)
    }

    fun goToPreviousFact() : Boolean{
        if(currentIndex > 0){
            currentIndex--
            return true
        }
        return false
    }

    fun goToNextFact(){
        if(currentIndex < (_catHistory.value?.size ?: 0) - 1){
            currentIndex++
        } else{
            functionsOfViewModel.getCatsFactUseCase()
        }

    }

    fun getPreviousHistory() : CatHistory? {
        val history = _catHistory.value ?: return null
        if(currentIndex > 0){
            currentIndex--
            return history[currentIndex]
        }
        return null
    }




//    fun getPreviousHistory() : CatHistory?{ actual
//        val currentCatsHistory = _catHistory.value
//
//        return currentCatsHistory?.lastOrNull()
////        return if(currentCatsHistory != null){
////            currentCatsHistory.last()
////        } else {
////            null
////        }
//    }
}



//val currentHistory = _history.value ?: return null
//return if (currentHistory.size > 1) {
//    currentHistory.removeAt(currentHistory.size - 1)
//    currentHistory.last()
//} else {
//    null
//}