package com.example.almazovaconsecutivepractices.data.repository

import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeFullEntity
import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeShortEntity
import com.example.almazovaconsecutivepractices.data.mock.AnimeData

/**
 * Репозиторий для получения информации об аниме
 */
class AnimeRepository {
    /**
     * Возвращает список сокращенной информации о тайтлах
     * @param word - ищет тайтлы в названиях которых есть эта подстрока,
     * если без параметра, то вернет полный список аниме
     */
    fun getShortList(word: String = ""): List<AnimeShortEntity> =
        AnimeData.animeShort.filter { it.title.contains(word, ignoreCase = true) }

    /**
     * Возвращает полную информацию о тайтле
     * @param id - идентификатор аниме
     */
    fun getById(id: Int): AnimeFullEntity? =
        AnimeData.animeFull.find { it.id == id }
}