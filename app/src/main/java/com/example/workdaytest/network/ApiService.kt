package com.example.workdaytest.network

import com.example.workdaytest.data.ImageSearchResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun searchImages(
            @Query("q") query: String,
            @Query("media_type") mediaType: String,
            @Query("page") page: Int,
            @Query("page_size") pageSize: Int
        ): ImageSearchResponse

}


val retrofit = Retrofit.Builder()
    .baseUrl("https://images-api.nasa.gov/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService = retrofit.create(ApiService::class.java)
