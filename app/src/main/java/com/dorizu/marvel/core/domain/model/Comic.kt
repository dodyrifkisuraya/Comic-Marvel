package com.dorizu.marvel.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comic (
    val id: String,
    val title: String?,
    val thumbnail: String?,
    val description: String?,
    val isFavorite: Boolean
) : Parcelable

@Parcelize
data class Thumbnail(
    val path: String,
    val extension: String
) : Parcelable