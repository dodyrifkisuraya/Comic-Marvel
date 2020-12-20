package com.dorizu.marvel.detail

import androidx.lifecycle.ViewModel
import com.dorizu.marvel.core.domain.model.Comic
import com.dorizu.marvel.core.domain.usecase.ComicsUseCase

class DetailComicViewModel(private val comicsUseCase: ComicsUseCase) : ViewModel() {
    fun setFavoriteComic(comic: Comic, newState: Boolean) = comicsUseCase.setFavorite(comic, newState)
}