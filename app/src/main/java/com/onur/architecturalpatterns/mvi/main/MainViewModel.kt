package com.onur.architecturalpatterns.mvi.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onur.architecturalpatterns.mvi.api.RetrofitInstance
import com.onur.architecturalpatterns.mvi.arch.IModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel(), IModel<MainState, MainIntent> {
    override val intents: Channel<MainIntent> = Channel(Channel.UNLIMITED)

    private val _state = MutableLiveData<MainState>()

    override val state: LiveData<MainState>
        get() = _state

    init {
        handlerIntent()
    }

    private fun handlerIntent() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect { userIntent ->
                when(userIntent) {
                    MainIntent.FetchPhotos -> fetchData()
                    MainIntent.RefreshPhotos -> TODO()
                    MainIntent.FetchNames -> TODO()
                }
            }
        }
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateState { MainState.Loading }
                updateState { MainState.ResultPhotos(RetrofitInstance.retrofitApi.fetchPhotos().body()!!) }
            } catch (e: Exception) {
                updateState { MainState.ResultError(e.message) }
            }
        }
    }

    private suspend fun updateState(handler: suspend () -> MainState) {
        _state.postValue(handler())
    }
}