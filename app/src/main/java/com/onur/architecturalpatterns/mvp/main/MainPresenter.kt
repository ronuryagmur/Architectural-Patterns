package com.onur.architecturalpatterns.mvp.main

import com.onur.architecturalpatterns.mvp.api.RetrofitInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(var mainView: IMainView?, val mainInteractor: MainInteractor) {

    fun fetchPhotos() {
        if (mainView == null) {
            return
        }

        mainView!!.showProgress()

        GlobalScope.launch {
            val response = RetrofitInstance.retrofitApi.fetchPhotos()
            if (response.isSuccessful) {
                mainView!!.onPhotosFetched(response.body()!!)
                mainView!!.hideProgress()
            } else {
                mainView!!.showErrorMessage(response.message())
                mainView!!.hideProgress()
            }
        }
    }

    fun onDestroy() {
        mainView = null
    }
}