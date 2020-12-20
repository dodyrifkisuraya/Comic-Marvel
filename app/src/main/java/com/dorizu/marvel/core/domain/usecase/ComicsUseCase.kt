package com.dorizu.marvel.core.domain.usecase

import com.dorizu.marvel.core.data.Resource
import com.dorizu.marvel.core.domain.model.Comic
import io.reactivex.Flowable

interface ComicsUseCase {

    fun getAllComic(): Flowable<Resource<List<Comic>>>
    fun getfavoriteComic(): Flowable<List<Comic>>
    fun setFavorite(comic: Comic, state: Boolean)

}