package com.dorizu.marvel.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ThumbnailResponse(
    @field:SerializedName("path")
    val path: String,

    @field:SerializedName("extension")
    val extension: String,
)