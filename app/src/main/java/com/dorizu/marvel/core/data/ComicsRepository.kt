package com.dorizu.marvel.core.data

import com.dorizu.marvel.core.data.source.local.LocalDataSource
import com.dorizu.marvel.core.data.source.remote.RemoteDataSource
import com.dorizu.marvel.core.data.source.remote.network.ApiResponse
import com.dorizu.marvel.core.data.source.remote.response.ComicResponse
import com.dorizu.marvel.core.domain.model.Comic
import com.dorizu.marvel.core.domain.repository.IComicRepository
import com.dorizu.marvel.core.utils.AppExecutors
import com.dorizu.marvel.core.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ComicsRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IComicRepository {

    companion object{
        @Volatile
        private var instance: ComicsRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): ComicsRepository = instance ?: synchronized(this){
            instance ?: ComicsRepository(remoteDataSource, localDataSource, appExecutors)
        }
    }

    override fun getAllComic(): Flowable<Resource<List<Comic>>> =
        object : NetworkBoundResource<List<Comic>, List<ComicResponse>>() {
            override fun loadFromDB(): Flowable<List<Comic>> {
                return localDataSource.getAllComic().map { DataMapper.mapEntitiesToDomain(it) }
            }

            //Apabila true maka ambil data dari internet
            // ganti dengan true jika ingin selalu mengambil data dari internet
            override fun shouldFetch(data: List<Comic>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flowable<ApiResponse<List<ComicResponse>>> =
                remoteDataSource.getAllComics()

            override fun saveCallResult(data: List<ComicResponse>) {
                val comicList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertComics(comicList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

        }.asFlowable()

    override fun getFavoritComic(): Flowable<List<Comic>> {
        return localDataSource.getFavoriteComic().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteComic(comic: Comic, state: Boolean) {
        val comicEntity = DataMapper.mapDomainToEntity(comic)
        appExecutors.diskIO().execute { localDataSource.setFavoriteComic(comicEntity, state) }
    }

}