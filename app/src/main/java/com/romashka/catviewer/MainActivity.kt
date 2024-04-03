package com.romashka.catviewer

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.gson.Gson
import com.romashka.catviewer.databinding.ActivityMainBinding
import com.romashka.catviewer.domain.CatImage
import com.romashka.catviewer.domain.CatsRepository
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.Random
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CatsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[CatsRepository::class.java]

        viewModel.catData.observe(this@MainActivity) {
            binding.textView.text = it.fact
        }

        binding.buttonNext.setOnClickListener {
            viewModel.getCatsFactUseCase()
        }


        binding.buttonPrevious.setOnClickListener {
            val previousFact = viewModel.getPreviousFact()
            if (previousFact != null) {
                viewModel._catData.value = previousFact
            }
        }


        viewModel.catImage.observe(this) {
            val url = viewModel.loadRandomCatImage()
            // val gson = Gson()
            val jsonData = JSONArray(url)
            val jsonObject = jsonData.getJSONObject(1)
            viewModel.catImage.observe(this) {
                Glide.with(this)
                    .load(jsonObject)
                    .fitCenter()
                    .into(binding.imageView)


            }
//        viewModel.catImage.observe(this){
//            Glide.with(this)
//                .load(URL)
//                .fitCenter()
//                .into(binding.imageView)
//        }




        }

        }

    companion object {
       const val URL = "https://api.thecatapi.com/v1/images/search"
    }

    }