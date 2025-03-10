package com.example.almazovaconsecutivepractices.data.mock

import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeFullEntity
import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeShortEntity
import com.example.almazovaconsecutivepractices.data.domain.entity.Tag

/**
 * Пример организации данных об аниме
 */
object AnimeData {
    val animeShort = listOf(
        AnimeShortEntity(
            id = 1,
            title = "Cowboy Bebop",
            coverImageMediumUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/small/bx1-CXtrrkMpJ8Zq.png",
            averageScore = 86
        ),
        AnimeShortEntity(
            id = 5,
            title = "Cowboy Bebop: The Movie - Knockin' on Heaven's Door",
            coverImageMediumUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/small/bx5-NozHwXWdNLCz.jpg",
            averageScore = 82
        ),
        AnimeShortEntity(
            id = 6,
            title = "Trigun",
            coverImageMediumUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/small/bx6-Zzun7PHNNgPt.jpg",
            averageScore = 80
        ),
        AnimeShortEntity(
            id = 7,
            title = "Witch Hunter ROBIN",
            coverImageMediumUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/small/bx7-6uh1fPvbgS9t.png",
            averageScore = 68
        ),
        AnimeShortEntity(
            id = 8,
            title = "Beet the Vandel Buster",
            coverImageMediumUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/small/b8-ReS3TwSgrDDi.jpg",
            averageScore = 65
        ),
        AnimeShortEntity(
            id = 15,
            title = "Eyeshield 21",
            coverImageMediumUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/small/bx15-A4F2t0TgWoi4.png",
            averageScore = 76
        ),
        AnimeShortEntity(
            id = 16,
            title = "Honey and Clover",
            coverImageMediumUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/small/bx16-5fJZ2Sy2ThRA.jpg",
            averageScore = 76
        )
    )

    val animeFull = listOf(
        AnimeFullEntity(
            id = 1,
            title = "Cowboy Bebop",
            coverImageLargeUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx1-CXtrrkMpJ8Zq.png",
            averageScore = 86,
            genres = listOf("Action", "Adventure", "Drama", "Sci-Fi"),
            tags = listOf(
                Tag("Space"), Tag("Crime"), Tag("Episodic"), Tag("Ensemble Cast"),
                Tag("Primarily Adult Cast"), Tag("Tragedy"), Tag("Noir"), Tag("Travel"),
                Tag("Guns"), Tag("Cyberpunk"), Tag("Male Protagonist"), Tag("Found Family"),
                Tag("Philosophy"), Tag("Terrorism"), Tag("Tomboy"), Tag("Cowboys"),
                Tag("Heterosexual"), Tag("Martial Arts"), Tag("Cyborg"), Tag("Amnesia"),
                Tag("Anti-Hero"), Tag("Gambling"), Tag("Yakuza"), Tag("Drugs"),
                Tag("Female Protagonist"), Tag("Police"), Tag("Cult"), Tag("Tanned Skin"),
                Tag("Nudity"), Tag("Circus")
            )
        ),
        AnimeFullEntity(
            id = 5,
            title = "Cowboy Bebop: The Movie - Knockin' on Heaven's Door",
            coverImageLargeUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx5-NozHwXWdNLCz.jpg",
            averageScore = 82,
            genres = listOf("Action", "Drama", "Mystery", "Sci-Fi"),
            tags = listOf(
                Tag("Terrorism"), Tag("Primarily Adult Cast"), Tag("Philosophy"),
                Tag("Crime"), Tag("Noir"), Tag("Anti-Hero"), Tag("Amnesia"),
                Tag("Space"), Tag("Guns"), Tag("Male Protagonist"), Tag("Cyberpunk"),
                Tag("Ensemble Cast"), Tag("Martial Arts"), Tag("Military"),
                Tag("Foreign"), Tag("Female Protagonist"), Tag("Work"),
                Tag("Cyborg"), Tag("Tomboy")
            )
        ),
        AnimeFullEntity(
            id = 6,
            title = "Trigun",
            coverImageLargeUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx6-Zzun7PHNNgPt.jpg",
            averageScore = 80,
            genres = listOf("Action", "Adventure", "Comedy", "Drama", "Sci-Fi"),
            tags = listOf(
                Tag("Guns"), Tag("Fugitive"), Tag("Male Protagonist"),
                Tag("Philosophy"), Tag("Primarily Adult Cast"), Tag("Cowboys"),
                Tag("Tragedy"), Tag("Crime"), Tag("Desert"), Tag("Travel"),
                Tag("Steampunk"), Tag("Post-Apocalyptic"), Tag("Shounen"),
                Tag("Episodic"), Tag("Twins"), Tag("Incest"), Tag("Aliens"),
                Tag("Space"), Tag("Slapstick"), Tag("Acrobatics"), Tag("Rural"),
                Tag("Religion"), Tag("Body Horror")
            )
        ),
        AnimeFullEntity(
            id = 7,
            title = "Witch Hunter ROBIN",
            coverImageLargeUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx7-6uh1fPvbgS9t.png",
            averageScore = 68,
            genres = listOf("Action", "Drama", "Mystery", "Supernatural"),
            tags = listOf(
                Tag("Conspiracy"), Tag("Police"), Tag("Female Protagonist"),
                Tag("Magic"), Tag("Urban Fantasy"), Tag("Noir"),
                Tag("Fugitive"), Tag("Witch"), Tag("Primarily Adult Cast"),
                Tag("Bar"), Tag("Kuudere")
            )
        ),
        AnimeFullEntity(
            id = 8,
            title = "Beet the Vandel Buster",
            coverImageLargeUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/b8-ReS3TwSgrDDi.jpg",
            averageScore = 65,
            genres = listOf("Adventure", "Fantasy", "Supernatural"),
            tags = listOf(Tag("Shounen"), Tag("Spearplay"), Tag("Swordplay"))
        ),
        AnimeFullEntity(
            id = 15,
            title = "Eyeshield 21",
            coverImageLargeUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx15-A4F2t0TgWoi4.png",
            averageScore = 76,
            genres = listOf("Action", "Comedy", "Sports"),
            tags = listOf(
                Tag("American Football"), Tag("Shounen"), Tag("Male Protagonist"),
                Tag("School"), Tag("Primarily Male Cast"), Tag("School Club"),
                Tag("Coming of Age"), Tag("Bullying"), Tag("Slapstick"),
                Tag("Delinquents"), Tag("Tragedy")
            )
        ),
        AnimeFullEntity(
            id = 16,
            title = "Honey and Clover",
            coverImageLargeUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx16-5fJZ2Sy2ThRA.jpg",
            averageScore = 76,
            genres = listOf("Comedy", "Drama", "Romance", "Slice of Life"),
            tags = listOf(
                Tag("Coming of Age"), Tag("Josei"), Tag("Male Protagonist"),
                Tag("College"), Tag("Primarily Adult Cast"), Tag("Heterosexual"),
                Tag("Love Triangle"), Tag("Primarily Male Cast"), Tag("Unrequited Love"),
                Tag("Drawing"), Tag("Chibi"), Tag("Kuudere")
            )
        )
    )
}