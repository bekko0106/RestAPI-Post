package developer.behzod.retrofit_one.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    const val BASE_URL= "https://hvax.pythonanywhere.com/"
    fun getRetrofitService():RetrofitService{
        val build = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
       return build.create(RetrofitService ::class.java)
    }
}