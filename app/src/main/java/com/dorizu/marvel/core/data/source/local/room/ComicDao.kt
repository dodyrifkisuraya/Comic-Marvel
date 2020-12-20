package com.dorizu.marvel.core.data.source.local.room

import androidx.room.*
import com.dorizu.marvel.core.data.source.local.entity.ComicEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ComicDao {
    @Query("SELECT * FROM comic")
    fun getAllComic(): Flowable<List<ComicEntity>>

    @Query("SELECT * FROM comic where isFavorite = 1")
    fun getFavoriteComic(): Flowable<List<ComicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComic(comics: List<ComicEntity>): Completable

    @Update
    fun updateFavoriteComic(comic: ComicEntity)
}