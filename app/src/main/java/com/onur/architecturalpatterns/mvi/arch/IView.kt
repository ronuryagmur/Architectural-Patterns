package com.onur.architecturalpatterns.mvi.arch

interface IView<S: IState> {
    fun render(state: S)
}