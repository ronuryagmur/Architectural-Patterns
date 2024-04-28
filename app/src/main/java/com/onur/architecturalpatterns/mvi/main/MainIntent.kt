package com.onur.architecturalpatterns.mvi.main

import com.onur.architecturalpatterns.mvi.arch.IIntent

sealed class MainIntent: IIntent {
    data object FetchPhotos: MainIntent()
    data object RefreshPhotos: MainIntent()
    data object FetchNames: MainIntent()
}