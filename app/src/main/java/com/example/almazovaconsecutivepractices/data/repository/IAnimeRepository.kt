package com.example.almazovaconsecutivepractices.data.repository

import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeFullEntity
import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeShortEntity

/**
 * Общение с аниме репозиторием
 */
interface IAnimeRepository {
    /**
     * Возвращает список сокращенной информации о тайтлах
     * @param word - ищет тайтлы в названиях которых есть эта подстрока
     */
//    suspend fun getList(word: String): List<AnimeShortEntity>

    /**
     * Возвращает полный список тайтлов
     */
//    suspend fun getList(): List<AnimeShortEntity> = getList("")

    /**
     * Возвращает полный список тайтлов с возможностью подгрузки
     */
    suspend fun getList(
        loadMore: Boolean = false
    ): List<AnimeShortEntity>

    /**
     * Возвращает полную информацию о тайтле
     * @param id - идентификатор аниме
     */
    suspend fun getById(id: Int): AnimeFullEntity?
}