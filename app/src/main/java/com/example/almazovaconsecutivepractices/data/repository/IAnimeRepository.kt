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
    fun getList(word: String): List<AnimeShortEntity>

    /**
     * Возвращает полный список тайтлов
     */
    fun getList(): List<AnimeShortEntity> = getList("")

    /**
     * Возвращает полную информацию о тайтле
     * @param id - идентификатор аниме
     */
    fun getById(id: Int): AnimeFullEntity?
}