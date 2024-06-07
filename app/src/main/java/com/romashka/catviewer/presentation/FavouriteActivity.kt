package com.romashka.catviewer.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent.DispatcherState
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.romashka.catviewer.presentation.catadapter.CatFavouriteAdapter
import com.romashka.catviewer.databinding.ActivityFavouriteBinding
import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.domain.repository.CatDatabaseRepository
import com.romashka.catviewer.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteActivity : AppCompatActivity(), CatFavouriteAdapter.DeleteDataByClickInterface {
    private lateinit var binding: ActivityFavouriteBinding

    private val appDatabase = AppDatabase
    private lateinit var getDatabaseWhole: List<CatData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            getDatabaseWhole = appDatabase.getDatabase(this@FavouriteActivity).catDao().getAll()
        }
        var adapter = CatFavouriteAdapter( getDatabaseWhole as ArrayList<CatData> , this)
        binding.recViewFavourite.layoutManager = LinearLayoutManager(this)
        binding.recViewFavourite.adapter = adapter

    }


    override fun deleteDataByClickInterface(data: CatData) {
        CoroutineScope(Dispatchers.IO).launch {
            appDatabase.getDatabase(this@FavouriteActivity).catDao().delete(data)
        }
    }

}