package com.dorizu.marvel.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.dorizu.marvel.core.domain.usecase.ComicsUseCase

class HomeViewModel(comicsUseCase: ComicsUseCase): ViewModel() {
    val comic = LiveDataReactiveStreams.fromPublisher(comicsUseCase.getAllComic())
}