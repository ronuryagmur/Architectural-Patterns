package com.onur.architecturalpatterns.mvi.api

import com.onur.architecturalpatterns.mvi.data.Photo
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/v2/list")
    suspend fun fetchPhotos(): Response<Array<Photo>>
}