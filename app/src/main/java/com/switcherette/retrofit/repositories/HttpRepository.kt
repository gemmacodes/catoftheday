package com.switcherette.retrofit.repositories

import android.util.Log
import com.switcherette.retrofit.data.BreedModel
import com.switcherette.retrofit.data.CatModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Headers
import retrofit2.http.Query

class HttpRepository {

    private val BASE_URL = "https://api.thecatapi.com/v1/"
    private val retrofit = createRetrofit()
    private val service: CatService = retrofit.create(CatService::class.java)

    // create retrofit
    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // implement interface functions (interface is also defined inside class)
    fun getAllBreeds(): List<BreedModel>? {
        val response = service.getAllBreeds().execute()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            Log.e("HTTP Error Tag", "${response.errorBody()}")
            return null
        }
    }

    fun getBreed(id: String?): List<CatModel>? {
        val response = service.getBreed(id).execute()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            Log.e("HTTP Error Tag", "${response.errorBody()}")
            return null
        }
    }

    interface CatService {
        // In the interface we will define the structure of our requests.

        @Headers("x-api-key: " + "805dfe81-c3a4-45e8-9560-2edbab910deb")
        @GET("breeds/")
        fun getAllBreeds(): Call<List<BreedModel>>

        @Headers("x-api-key: " + "805dfe81-c3a4-45e8-9560-2edbab910deb")
        @GET("images/search")
        fun getBreed(@Query("breed_ids") id: String?): Call<List<CatModel>>

    }

}