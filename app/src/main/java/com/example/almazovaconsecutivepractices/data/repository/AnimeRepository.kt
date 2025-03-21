package com.example.almazovaconsecutivepractices.data.repository

import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeFullEntity
import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeShortEntity
import com.example.almazovaconsecutivepractices.data.mock.AnimeData

/**
 * Репозиторий для получения информации об аниме
 */
class AnimeRepository : IAnimeRepository {

    override fun getList(word: String): List<AnimeShortEntity> =
        AnimeData.animeShort.filter { it.title.contains(word, ignoreCase = true) }

    override fun getById(id: Int): AnimeFullEntity? =
        AnimeData.animeFull.find { it.id == id }
}