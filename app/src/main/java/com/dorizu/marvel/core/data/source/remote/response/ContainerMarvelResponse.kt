package com.dorizu.marvel.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ContainerMarvelResponse(

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("data")
    val data: DataResponse
)