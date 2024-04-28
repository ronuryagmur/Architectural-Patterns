package com.onur.architecturalpatterns.mvp.main

import com.onur.architecturalpatterns.mvp.api.RetrofitInstance
import com.onur.architecturalpatterns.mvp.data.Photo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainInteractor {

    interface OnFetchListener {
        fun onSuccess()
        fun onError()
    }
    fun fetch(listener: OnFetchListener): Array<Photo> {
        GlobalScope.launch {
            val response = RetrofitInstance.retrofitApi.fetchPhotos()
            if (response.isSuccessful) {
                listener.onSuccess()
            } else {
                listener.onError()
            }
        }
        return arrayOf()
    }
}