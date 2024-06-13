package com.romashka.catviewer.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.romashka.catviewer.presentation.catadapter.CatFavouriteAdapter
import com.romashka.catviewer.databinding.ActivityFavouriteBinding
import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.presentation.viewmodels.FavouriteViewModel
import com.romashka.catviewer.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteActivity : AppCompatActivity(), CatFavouriteAdapter.DeleteDataByClickInterface {
    private lateinit var binding: ActivityFavouriteBinding
    private lateinit var viewModelFavourite: FavouriteViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelFavourite = ViewModelProvider(this)[FavouriteViewModel::class.java]


        val getAllLiveDataDao = viewModelFavourite.appDatabase.initDatabase(this).catDao().getAll()

        getAllLiveDataDao.observe(this){
            val adapter = CatFavouriteAdapter( it as ArrayList<CatData> , this@FavouriteActivity)
            binding.recViewFavourite.layoutManager = LinearLayoutManager(this)
            binding.recViewFavourite.adapter = adapter
        }

    }


    override fun deleteDataByClickInterface(data: CatData) {
        CoroutineScope(Dispatchers.IO).launch {
            viewModelFavourite.appDatabase.initDatabase(this@FavouriteActivity).catDao().delete(data)
        }
    }

}