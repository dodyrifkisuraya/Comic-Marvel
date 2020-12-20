package com.dorizu.marvel.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @field:SerializedName("results")
    val results: List<ComicResponse>
)