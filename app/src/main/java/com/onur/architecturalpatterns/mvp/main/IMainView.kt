package com.onur.architecturalpatterns.mvp.main

import com.onur.architecturalpatterns.mvp.data.Photo

interface IMainView {
    fun onPhotosFetched(photos: Array<Photo>)
    fun showProgress()
    fun hideProgress()
    fun showErrorMessage(message: String)
}