package com.dorizu.marvel.favorit

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.dorizu.marvel.core.domain.usecase.ComicsUseCase

class FavoriteViewModel(comicsUseCase: ComicsUseCase): ViewModel() {
    val favoriteComic = LiveDataReactiveStreams.fromPublisher(comicsUseCase.getfavoriteComic())
}