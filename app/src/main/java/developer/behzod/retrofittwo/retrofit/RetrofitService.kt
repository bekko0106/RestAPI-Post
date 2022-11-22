package developer.behzod.retrofit_one.retrofit

import developer.behzod.retrofit_one.models.MyToDoGetResponse
import developer.behzod.retrofit_one.models.MyToDoPostRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @GET("plan")
    fun getAllTodo(): Call<List<MyToDoGetResponse>>

    @POST("plan")
    fun addToDo(@Body myToDoPostRequest: MyToDoPostRequest): Call<MyToDoGetResponse>

}