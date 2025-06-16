package com.example.almazovaconsecutivepractices.listWithDetails.presentation.viewModel

import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeFullEntity
import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeShortEntity

/**
 * Преобразования данных для отображения
 */
object MapperAnime {
    /**
     * Изменение формата оценки для сокращенных данных
     */
    fun AnimeShortEntity.formatAverageScore(): String {
        if (averageScore == null)
            return "0.0"
        return formatAverageScore(averageScore)
    }

    /**
     * Изменение формата оценки для основных данных
     */
    fun AnimeFullEntity.formatAverageScore(): String {
        if (averageScore == null)
            return "0.0"
        return formatAverageScore(averageScore)
    }

    /**
     * Форматирование оценки аниме
     */
    fun formatAverageScore(score: Int): String {
        return (score.toFloat() / 10).toString()
    }
}