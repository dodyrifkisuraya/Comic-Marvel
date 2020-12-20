package com.dorizu.marvel.core.utils

import com.dorizu.marvel.core.data.source.local.entity.ComicEntity
import com.dorizu.marvel.core.data.source.remote.response.ComicResponse
import com.dorizu.marvel.core.domain.model.Comic

object DataMapper {
    fun mapResponsesToEntities(input: List<ComicResponse>): List<ComicEntity>{
        val comicList = ArrayList<ComicEntity>()
        input.map {
            val comic = ComicEntity(
                id = it.id,
                title = it.title,
                description = it.description,
                thumbnail = it.thumbnail?.path + "/portrait_uncanny." +it.thumbnail?.extension,
                isFavorite = false
            )
            comicList.add(comic)
        }
        return comicList
    }

    fun mapEntitiesToDomain(input: List<ComicEntity>): List<Comic> =
        input.map {
            Comic(
                id = it.id,
                title = it.title,
                description = it.description,
                thumbnail = it.thumbnail,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Comic) = ComicEntity(
        id = input.id,
        title = input.title,
        description = input.description,
        thumbnail = input.thumbnail,
        isFavorite = input.isFavorite
    )
}