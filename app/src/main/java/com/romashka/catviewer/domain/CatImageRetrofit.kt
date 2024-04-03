//package com.romashka.catviewer.domain
//
//import com.romashka.catviewer.data.CatImageApi
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//class CatImageRetrofit {
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("https://api.thecatapi.com/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    private val catApi = retrofit.create(CatImageApi::class.java)
//
//    fun loadRandomCatImage(onSuccess: (String) -> Unit) {
//        val call = catApi.getCatImage()
//        call.enqueue(object : Callback<CatImage> {
//            override fun onResponse(call: Call<CatImage>, response: Response<CatImage>) {
//                if (response.isSuccessful) {
//                    response.body()?.let { catImageResponse ->
//                        onSuccess(catImageResponse.url)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<CatImage>, t: Throwable) {
//                t.message
//            }
//        })
//    }
//}