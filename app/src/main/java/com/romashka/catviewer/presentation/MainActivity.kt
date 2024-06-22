package com.romashka.catviewer.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.romashka.catviewer.data.CatNetwork
import com.romashka.catviewer.databinding.ActivityMainBinding
import com.romashka.catviewer.domain.GetCatImage
import com.romashka.catviewer.domain.GetCatsFact
import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.domain.repository.CatRepository
import com.romashka.catviewer.domain.repository.CatsRepositoryImage
import com.romashka.catviewer.presentation.viewmodels.MainViewModel
import com.romashka.catviewer.presentation.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModelFactory = MainViewModelFactory(
            GetCatsFact(),
            GetCatImage(), application
        )
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]


        viewModel.currentCatData.observe(this) { newData ->
            val url = newData?.url
            Glide.with(this)
                .load(url)
                .into(binding.textviewAndImageview.imageView)
            binding.textviewAndImageview.textView.text = newData?.fact

            binding.imageViewAddToFavourite.setOnClickListener {
                val savedInfo = newData ?: CatData("", "")
                viewModel.saveData(savedInfo)
                Toast.makeText(this, "Cat's data successfully saved", Toast.LENGTH_SHORT).show()
            }

            viewModel.getInfoCatData()
        }

        binding.buttonNext.setOnClickListener {
            viewModel.nextClickPage()
        }


        binding.buttonPrevious.setOnClickListener {
            viewModel.goToThePreviousPage()
        }

        binding.buttonGoToFavourite.setOnClickListener {
            val intent = Intent(this@MainActivity, FavouriteActivity::class.java)
            startActivity(intent)
        }

    }


}