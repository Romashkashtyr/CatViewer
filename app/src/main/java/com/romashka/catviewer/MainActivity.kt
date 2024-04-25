package com.romashka.catviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.romashka.catviewer.databinding.ActivityMainBinding
import com.romashka.catviewer.domain.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

      //  val imageUseCase = GetCatsFactUseCase(viewModel) -> actual


        viewModel.catImage.observe(this){
            val url = it.firstOrNull()
            Glide.with(this)
                .load(url?.url)
                .into(binding.imageView)
        }



        viewModel.catData.observe(this@MainActivity) {
            binding.textView.text = it.fact
        }



        binding.buttonNext.setOnClickListener {
            viewModel.moveToNextPage()
           // viewModel.getCatsFactUseCase() -> first three codes are actual
          //  viewModel.addToHistory(viewModel.getCatsFactUseCase())
          //  imageUseCase.getCatsImageUseCase(this, binding.imageView)
            //viewModel.catImage.value?.let { it1 ->
//                historyViewModel.addToHistory(viewModel.catData.value.toString(),
//                    it1
//                )
    //        }



        }



        //imageUseCase.getCatsImageUseCase(this, binding.imageView) - > actual



        binding.buttonPrevious.setOnClickListener {
           // val previousFact = //historyViewModel.getPreviousHistory()
//            if(previousFact  != null){
//                binding.textView.text = previousFact.fact
//            }

           // val lastUploadedImage = viewModel.lastImage(this, binding.imageView) -> actual

//            Glide.with(this) -> Actual
//                .load(lastUploadedImage)
//                .into(binding.imageView)
        }



//        binding.buttonPrevious.setOnClickListener {
////            val previousFact = viewModel.getPreviousFact()
////            if (previousFact != null) {
////                viewModel._catData.value = previousFact
////            }
//        }





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