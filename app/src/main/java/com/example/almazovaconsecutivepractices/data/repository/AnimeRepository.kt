package com.example.almazovaconsecutivepractices.data.repository

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.example.GetAnimeFullQuery
import com.example.GetAnimeListQuery
import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeFullEntity
import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeShortEntity


/**
 * Репозиторий для получения информации об аниме
 */
class AnimeRepository(
    private val apolloClient: ApolloClient
) : IAnimeRepository {

    private var currentPage = 1
    private val perPage = 20

    override suspend fun getList(
        loadMore: Boolean
    ): List<AnimeShortEntity> {
        if (!loadMore) {
            currentPage = 1
        }
        val result = apolloClient.query(
            GetAnimeListQuery(
                page = Optional.present(currentPage),
                perPage = Optional.present(perPage)
            )
        ).execute()
            .data
            ?.toAnimeList() ?: emptyList()

        if (result.isNotEmpty()) {
            currentPage++
        }
        return result
    }

    override suspend fun getById(id: Int): AnimeFullEntity? {
        return apolloClient.query(
            GetAnimeFullQuery(id = Optional.present(id))
        ).execute()
            .data
            ?.Media
            ?.toFullEntity()
    }
}