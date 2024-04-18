package com.romashka.catviewer.domain

//class GetCatsFactUseCase(private val repository: CatsViewModel)
class GetCatsFactUseCase(private val repository: CatRepository)  {

    suspend fun invoke() : CatFactResponse {
        return repository.getCatFact()
    }



//    fun getCatsFactUseCase(): LiveData<CatFactResponse> {
//        return repository.getCatsFactUseCase()
//    }
//
//    fun getCatsImageUseCase(context: Context, imageView: ImageView){
//        repository.loadRandomCatImage(context, imageView)
//    }
}