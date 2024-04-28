package com.onur.architecturalpatterns.mvvm.main

import com.onur.architecturalpatterns.mvvm.data.Photo
import com.onur.architecturalpatterns.mvvm.api.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun fetchPhotos(): Response<Array<Photo>> {
        return RetrofitInstance.retrofitApi.fetchPhotos()
    }
}