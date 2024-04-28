package com.onur.architecturalpatterns.mvi.main

import com.onur.architecturalpatterns.mvi.arch.IState
import com.onur.architecturalpatterns.mvi.data.Photo

sealed class MainState: IState {
    data object Loading: MainState()
    data object Exception: MainState()
    data class ResultPhotos(val photos: Array<Photo>): MainState()
    data class ResultError(val errorMessage: String?): MainState()
}