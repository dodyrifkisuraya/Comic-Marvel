package com.dorizu.marvel.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.dorizu.marvel.BuildConfig
import com.dorizu.marvel.core.data.source.remote.network.ApiResponse
import com.dorizu.marvel.core.data.source.remote.network.ApiService
import com.dorizu.marvel.core.data.source.remote.response.ComicResponse
import com.dorizu.marvel.core.utils.Utils
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.*

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    val defaultLimit = 30
    var countLimit = 0

    @SuppressLint("CheckResult")
    fun getAllComics(): Flowable<ApiResponse<List<ComicResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<ComicResponse>>>()

        val timestamp = Date().time
        val hash = Utils.md5(timestamp.toString()+ BuildConfig.MarvelPrivateApiKey+BuildConfig.MarvelPublicApiKey)

        Log.e("API-ts", timestamp.toString())
        Log.e("API-hash", hash)

        val client = apiService.getComicList(timestamp.toString(), BuildConfig.MarvelPublicApiKey, hash, defaultLimit)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataResponse = response.data.results
                resultData.onNext(if (dataResponse.isNotEmpty()) ApiResponse.Success(dataResponse) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}