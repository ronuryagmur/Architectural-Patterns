package com.onur.architecturalpatterns.mvvm.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onur.architecturalpatterns.mvvm.data.Photo
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val repository = Repository()

    private val _photoList = MutableLiveData<Array<Photo>>()
    val photoList: LiveData<Array<Photo>> = _photoList

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _showSnackbar = MutableLiveData<String>()
    val showSnackbar: LiveData<String> = _showSnackbar

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        _dataLoading.postValue(true)
        viewModelScope.launch {
            val response = repository.fetchPhotos()
            if (response.isSuccessful) {
                _photoList.postValue(response.body())
                _dataLoading.postValue(false)
            } else {
                _showSnackbar.postValue(response.message())
                _dataLoading.postValue(false)
            }
        }
    }
}