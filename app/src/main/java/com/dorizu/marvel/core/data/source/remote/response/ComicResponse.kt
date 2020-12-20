package com.dorizu.marvel.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ComicResponse(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse? = null
)