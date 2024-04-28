package com.onur.architecturalpatterns.mvp.api

import com.onur.architecturalpatterns.mvp.data.Photo
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/v2/list")
    suspend fun fetchPhotos(): Response<Array<Photo>>
}