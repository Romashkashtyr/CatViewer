package com.romashka.catviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.romashka.catviewer.databinding.ActivityMainBinding
import com.romashka.catviewer.domain.CatsRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CatsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[CatsRepository::class.java]

        viewModel.catData.observe(this@MainActivity){
            binding.textView.text = it.fact
        }

        binding.buttonNext.setOnClickListener {
            viewModel.getCatsFactUseCase()
        }


        binding.buttonPrevious.setOnClickListener {
           val previousFact =  viewModel.getPreviousFact()
            if(previousFact != null) {
                viewModel._catData.value = previousFact
            }
        }

        Glide.with(this).load("https://api.thecatapi.com/v1/images/search/").into(binding.imageView)

    }

}