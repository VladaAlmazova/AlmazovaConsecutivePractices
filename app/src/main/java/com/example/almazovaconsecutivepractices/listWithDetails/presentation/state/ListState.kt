package com.example.almazovaconsecutivepractices.listWithDetails.presentation.state

import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeShortEntity

/**
 * Информация которая должна быть доступна UI
 */
interface ListState {
    val items: List<AnimeShortEntity>
    val isEmpty: Boolean
}