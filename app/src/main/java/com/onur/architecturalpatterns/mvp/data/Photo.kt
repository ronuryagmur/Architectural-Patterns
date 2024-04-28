package com.onur.architecturalpatterns.mvp.data

import com.google.gson.annotations.SerializedName

data class Photo(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    @SerializedName("download_url")
    val downloadUrl: String
)
