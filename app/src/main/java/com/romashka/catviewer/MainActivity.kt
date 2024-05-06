package com.romashka.catviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.romashka.catviewer.data.CatNetwork
import com.romashka.catviewer.databinding.ActivityMainBinding
import com.romashka.catviewer.domain.GetCatImage
import com.romashka.catviewer.domain.GetCatsFact
import com.romashka.catviewer.domain.repository.CatRepository
import com.romashka.catviewer.domain.repository.CatsRepositoryImage
import com.romashka.catviewer.domain.repository.viewmodels.MainViewModel
import com.romashka.catviewer.domain.repository.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModelFactory = MainViewModelFactory(
            GetCatsFact(CatRepository(
            CatNetwork.catFactApi)),
        GetCatImage(CatsRepositoryImage(CatNetwork.catApiImage)))
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]



        viewModel.catDataHistory.observe(this) {
            val url = it?.url
            Glide.with(this)
                .load(url)
                .into(binding.imageView)
        }


        viewModel.catDataHistory.observe(this@MainActivity) {
            binding.textView.text = it?.fact
        }


        binding.buttonNext.setOnClickListener {
                viewModel.nextClickPage()
            }


        binding.buttonPrevious.setOnClickListener {
            viewModel.goToThePreviousPage()
        }


    }

    companion object {
        const val URL = "https://api.thecatapi.com/v1/images/search"
    }

}