package com.example.almazovaconsecutivepractices.data.repository

import com.example.GetAnimeFullQuery
import com.example.GetAnimeListQuery

import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeFullEntity
import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeShortEntity


fun GetAnimeListQuery.Data.toAnimeList(): List<AnimeShortEntity> {
    return this.Page?.media?.filterNotNull()?.map { media ->
        AnimeShortEntity(
            id = media.id ?: 0,
            title = media.title?.english.orEmpty(),
            coverImageMediumUrl = media.coverImage?.medium.orEmpty(),
            averageScore = media.averageScore?.toInt()
        )
    } ?: emptyList()
}

fun GetAnimeFullQuery.Media.toFullEntity(): AnimeFullEntity = AnimeFullEntity(
    id = id ?: 0,
    title = title?.english.orEmpty(),
    coverImageExtraLargeUrl = coverImage?.extraLarge.orEmpty(),
    averageScore = averageScore?.toInt(),
    genres = genres?.filterNotNull().orEmpty(),
    isSaved = false
)