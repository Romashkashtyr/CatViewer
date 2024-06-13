package com.romashka.catviewer.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.presentation.catadapter.CatFavouriteAdapter
import com.romashka.catviewer.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteViewModel : ViewModel() {

    val appDatabase = AppDatabase
    //private lateinit var catDataList: LiveData<List<CatData>>

//    fun getFavouriteDataDao(){
//        CoroutineScope(Dispatchers.IO).launch {
//            catDataList = appDatabase.initDatabase(application.applicationContext).catDao().getAll()
//        }
//    }


}