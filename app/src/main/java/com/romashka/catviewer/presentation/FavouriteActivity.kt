package com.romashka.catviewer.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.romashka.catviewer.presentation.catadapter.CatFavouriteAdapter
import com.romashka.catviewer.databinding.ActivityFavouriteBinding
import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.presentation.viewmodels.FavouriteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteActivity : AppCompatActivity(), CatFavouriteAdapter.ItemClickListener {
    private lateinit var binding: ActivityFavouriteBinding
    private lateinit var viewModelFavourite: FavouriteViewModel
    private lateinit var adapter: CatFavouriteAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelFavourite = ViewModelProvider(this)[FavouriteViewModel::class.java]
        viewModelFavourite.getData()




        viewModelFavourite.favouriteListSavedCatData.observe(this){
            adapter = CatFavouriteAdapter( it.toMutableList() , this@FavouriteActivity)
            binding.recViewFavourite.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            binding.recViewFavourite.adapter = adapter
        }

    }


    override fun deleteItem(item: CatData, position: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            viewModelFavourite.deleteCatData(item)
        }
        adapter.deleteItem(position)
    }

}