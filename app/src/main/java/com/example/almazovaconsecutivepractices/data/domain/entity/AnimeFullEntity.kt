package com.example.almazovaconsecutivepractices.data.domain.entity

class AnimeFullEntity(
    val id: Int,
    val title: String,
    val coverImageLargeUrl: String,
    val averageScore: Int,
    val genres: List<String>,
    val tags: List<Tag>
)

class Tag(
    val name: String
)