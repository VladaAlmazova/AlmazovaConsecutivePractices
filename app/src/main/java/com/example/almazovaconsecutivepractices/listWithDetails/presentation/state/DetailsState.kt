package com.example.almazovaconsecutivepractices.listWithDetails.presentation.state

import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeFullEntity

interface DetailsState {
    val anime: AnimeFullEntity?
    val rating: Float
    val isRatingVisible: Boolean
    val isSavedAnime: Boolean
}