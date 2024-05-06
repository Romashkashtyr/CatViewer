package com.romashka.catviewer.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.romashka.catviewer.databinding.ActivityFavouriteBinding

class FavouriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavouriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}