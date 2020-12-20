package com.dorizu.marvel.core.data.source.remote.network

import com.dorizu.marvel.core.data.source.remote.response.ContainerMarvelResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET("/v1/public/comics")
    fun getComicList(@Query("ts") timeStamp: String,
                     @Query("apikey") apiKey: String,
                     @Query("hash") hash: String,
                     @Query("limit") limit: Int)
            : Flowable<ContainerMarvelResponse>
}