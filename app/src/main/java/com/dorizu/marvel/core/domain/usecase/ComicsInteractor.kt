package com.dorizu.marvel.core.domain.usecase

import com.dorizu.marvel.core.data.Resource
import com.dorizu.marvel.core.domain.model.Comic
import com.dorizu.marvel.core.domain.repository.IComicRepository
import io.reactivex.Flowable

class ComicsInteractor(private val comicRepository: IComicRepository): ComicsUseCase {

    override fun getAllComic(): Flowable<Resource<List<Comic>>> = comicRepository.getAllComic()

    override fun getfavoriteComic(): Flowable<List<Comic>> = comicRepository.getFavoritComic()

    override fun setFavorite(comic: Comic, state: Boolean) = comicRepository.setFavoriteComic(comic, state)

}