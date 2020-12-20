package com.dorizu.marvel.core.di

import android.content.Context
import com.dorizu.marvel.core.data.ComicsRepository
import com.dorizu.marvel.core.data.source.local.LocalDataSource
import com.dorizu.marvel.core.data.source.local.room.ComicDatabase
import com.dorizu.marvel.core.data.source.remote.RemoteDataSource
import com.dorizu.marvel.core.data.source.remote.network.ApiConfig
import com.dorizu.marvel.core.domain.repository.IComicRepository
import com.dorizu.marvel.core.domain.usecase.ComicsInteractor
import com.dorizu.marvel.core.domain.usecase.ComicsUseCase
import com.dorizu.marvel.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IComicRepository{
        val database = ComicDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.comicDao())
        val appExecutors = AppExecutors()

        return ComicsRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideComicUseCase(context: Context): ComicsUseCase{
        val repository = provideRepository(context)
        return ComicsInteractor(repository)
    }
}