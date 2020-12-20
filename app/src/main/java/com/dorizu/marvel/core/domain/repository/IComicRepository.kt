package com.dorizu.marvel.core.domain.repository

import com.dorizu.marvel.core.data.Resource
import com.dorizu.marvel.core.domain.model.Comic
import io.reactivex.Flowable

interface IComicRepository {

    fun getAllComic(): Flowable<Resource<List<Comic>>>

    fun getFavoritComic(): Flowable<List<Comic>>

    fun setFavoriteComic(comic: Comic, state: Boolean)

}