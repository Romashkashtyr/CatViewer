package com.romashka.catviewer.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.romashka.catviewer.presentation.catadapter.CatFavouriteAdapter
import com.romashka.catviewer.databinding.ActivityFavouriteBinding
import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.presentation.viewmodels.AppDatabaseViewModel

class FavouriteActivity : AppCompatActivity(), CatFavouriteAdapter.DeleteDataByClickInterface {
    private lateinit var binding: ActivityFavouriteBinding
    private lateinit var databaseViewModel: AppDatabaseViewModel
    private lateinit var listCatInfo: List<CatData>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseViewModel = ViewModelProvider(this)[AppDatabaseViewModel::class.java]

        val adapter = CatFavouriteAdapter(arrayListOf(), this)
        binding.recViewFavourite.layoutManager = LinearLayoutManager(this)
        binding.recViewFavourite.adapter = adapter

    }

    override fun deleteDataByClickInterface(data: CatData) {
        databaseViewModel.deleteCat(data)
        }


}