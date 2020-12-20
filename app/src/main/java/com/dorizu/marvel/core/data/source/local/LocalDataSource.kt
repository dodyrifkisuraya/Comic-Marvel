package com.dorizu.marvel.core.data.source.local

import com.dorizu.marvel.core.data.source.local.entity.ComicEntity
import com.dorizu.marvel.core.data.source.local.room.ComicDao
import io.reactivex.Flowable

class LocalDataSource private constructor(private val comicDao: ComicDao){
    companion object{
        private var instance: LocalDataSource? = null

        fun getInstance(comicDao: ComicDao): LocalDataSource =
            instance ?: synchronized(this){
                instance ?: LocalDataSource(comicDao)
            }
    }

    fun getAllComic(): Flowable<List<ComicEntity>> = comicDao.getAllComic()

    fun getFavoriteComic(): Flowable<List<ComicEntity>> = comicDao.getFavoriteComic()

    fun insertComics(comics: List<ComicEntity>) = comicDao.insertComic(comics)

    fun setFavoriteComic(comicEntity: ComicEntity, newState: Boolean){
        comicEntity.isFavorite = newState
        comicDao.updateFavoriteComic(comicEntity)
    }
}