package com.onur.architecturalpatterns.mvvm.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://picsum.photos/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofitApi by lazy {
        retrofit.create(ApiService::class.java)
    }
}