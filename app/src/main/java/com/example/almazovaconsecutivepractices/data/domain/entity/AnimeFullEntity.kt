package com.example.almazovaconsecutivepractices.data.domain.entity

class AnimeFullEntity(
    val id: Int,
    val title: String,
    val coverImageExtraLargeUrl: String,
    val averageScore: Int,
    val genres: List<String>,
    var isSaved: Boolean = false
)