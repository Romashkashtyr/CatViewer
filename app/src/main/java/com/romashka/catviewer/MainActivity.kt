package com.romashka.catviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.romashka.catviewer.databinding.ActivityMainBinding
import com.romashka.catviewer.domain.CatsViewModel
import com.romashka.catviewer.domain.GetCatsFactUseCase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CatsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[CatsViewModel::class.java]

        val imageUseCase = GetCatsFactUseCase(viewModel)

        viewModel.catData.observe(this@MainActivity) {
            binding.textView.text = it.fact
        }

        binding.buttonNext.setOnClickListener {
            viewModel.getCatsFactUseCase()
        }


//        binding.buttonPrevious.setOnClickListener {
////            val previousFact = viewModel.getPreviousFact()
////            if (previousFact != null) {
////                viewModel._catData.value = previousFact
////            }
//        }


        imageUseCase.getCatsImageUseCase(this, binding.imageView)


//        viewModel.catImage.observe(this) {
//
//
////            val catImageGetByMoshi = catAdapterMoshi.fromJson(url)
//
//            viewModel.catImage.observe(this) {
//                Glide.with(this)
//                    .load(it.url)
//                    .fitCenter()
//                    .into(binding.imageView)
//            }
//        }
//
//        }
    }

    companion object {
       const val URL = "https://api.thecatapi.com/v1/images/search"
    }

    }