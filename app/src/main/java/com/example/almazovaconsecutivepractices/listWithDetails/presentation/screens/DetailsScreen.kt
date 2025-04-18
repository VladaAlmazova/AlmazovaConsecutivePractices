package com.example.almazovaconsecutivepractices.listWithDetails.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.AsyncImage
import com.example.almazovaconsecutivepractices.R
import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeFullEntity
import com.example.almazovaconsecutivepractices.listWithDetails.presentation.screens.components.SliderRating
import com.example.almazovaconsecutivepractices.listWithDetails.presentation.viewModel.DetailsViewModel
import com.example.almazovaconsecutivepractices.listWithDetails.presentation.viewModel.MapperAnime.formatAverageScore
import com.example.almazovaconsecutivepractices.ui.theme.MyColors
import com.example.almazovaconsecutivepractices.ui.theme.Spacing
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

/**
 * UI экрана с деталями
 */
@Composable
fun DetailsContent(titleId: Int, modifier: Modifier = Modifier) {
    val stackNavigation = LocalStackNavigation.current
    val viewModel = koinViewModel<DetailsViewModel> { parametersOf(stackNavigation, titleId) }
    val anime = viewModel.viewState.anime
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { viewModel.back() }) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад",
                            tint = Color.White
                        )
                    }
                },
            )
        },
        content = { paddingValues ->
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            )
            {
                val (backRef) = createRefs()
                anime?.let { nonNullAnime ->
                    ConstraintLayoutContent(nonNullAnime, viewModel)
                } ?: run {
                    FullscreenText(stringResource(R.string.anime_empty_result))
                }
            }
        }
    )
}

/**
 * Реализация Screen
 */
@Parcelize
class DetailsScreen(val titleId: Int, override val screenKey: ScreenKey = generateScreenKey()) :
    Screen {
    @Composable
    override fun Content(modifier: Modifier) {
        DetailsContent(titleId, modifier)
    }
}


//@Parcelize
//class DetailsScreen(
//    val titleId: Int, override val screenKey: ScreenKey = generateScreenKey()
//) : Screen {
//
//    @Composable
//    override fun Content(modifier: Modifier) {
//        val stackNavigation = LocalStackNavigation.current
//
//
//        val viewModel = koinViewModel<DetailsViewModel> { parametersOf(stackNavigation, titleId) }
//        val anime = viewModel.viewState.anime
//
//        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
//            val (backRef) = createRefs()
//            anime?.let { nonNullAnime ->
//                ConstraintLayoutContent(nonNullAnime, viewModel)
//            } ?: run {
//                FullscreenText(stringResource(R.string.anime_empty_result))
//            }
//
//            CircularBackButton(
//                onBackClick = { viewModel.back() },
//                modifier = Modifier.constrainAs(backRef) {
//                    top.linkTo(parent.top)
//                    start.linkTo(parent.start)
//                })
//        }
//    }
//}

/**
 * выводит крупный текст на весь экран
 */
@Composable
fun FullscreenText(text: String) {
    Text(
        text = text,
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h1
    )
}

@Composable
fun ConstraintLayoutContent(animeFullEntity: AnimeFullEntity, viewModel: DetailsViewModel) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (imgRef, titleRef, scoreRef, myScoreRef, genresRef, tagsRef, saveRef) = createRefs()

        TitleImg(
            modifier = Modifier.constrainAs(imgRef) {
                top.linkTo(parent.top)
            }, imgURL = animeFullEntity.coverImageExtraLargeUrl
        )

        Text(text = animeFullEntity.title, modifier = Modifier
            .constrainAs(titleRef) {
                top.linkTo(
                    anchor = imgRef.bottom, margin = Spacing.medium
                )
                start.linkTo(parent.start)
            }
            .padding(horizontal = Spacing.medium), style = MaterialTheme.typography.h5)

        SaveToCollectionButton(modifier = Modifier.constrainAs(saveRef) {
            bottom.linkTo(anchor = imgRef.bottom, margin = Spacing.small)
            end.linkTo(
                anchor = parent.end, margin = Spacing.medium
            )
        },
            onClick = { viewModel.onSavedChanged(!viewModel.viewState.isSavedAnime) },
            isSaved = viewModel.viewState.isSavedAnime
        )

        ScrollableGenres(items = animeFullEntity.genres,
            modifier = Modifier.constrainAs(genresRef) {
                top.linkTo(
                    anchor = titleRef.bottom, margin = Spacing.medium
                )
            })

        Box(modifier = Modifier.constrainAs(scoreRef) {
            top.linkTo(
                anchor = genresRef.bottom, margin = Spacing.medium
            )
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            LargeTextAverageScore(animeFullEntity.averageScore)
        }

        SliderRating(modifier = Modifier.constrainAs(myScoreRef) {
            top.linkTo(scoreRef.bottom, margin = Spacing.medium)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        },
            rating = viewModel.viewState.rating,
            onRatingChanged = { newRating -> viewModel.onRatingChanged(newRating) })
    }
}

@Composable
fun SaveToCollectionButton(modifier: Modifier, isSaved: Boolean = false, onClick: () -> Unit = {}) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(if (isSaved) Color.Gray else Color.LightGray)
    ) {
        Icon(
            imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
            contentDescription = "Save",
            tint = if (isSaved) Color.White else Color.Black
        )
    }
}

@Composable
fun genreItem(genre: String) {
    Box(
        modifier = Modifier
            .background(MyColors.DarkBlue, shape = RoundedCornerShape(50.dp))
            .padding(horizontal = Spacing.small, vertical = Spacing.extraSmall)
    ) {
        Text(
            text = genre, color = Color.White, style = MaterialTheme.typography.subtitle2
        )
    }
}

@Preview
@Composable
fun prevgenreItem() {
    genreItem("текст")
}

@Composable
fun ScrollableGenres(
    items: List<String>, modifier: Modifier
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
            .padding(Spacing.small),
        horizontalArrangement = Arrangement.spacedBy(Spacing.extraSmall, Alignment.Start),
    ) {
        items.forEach { item ->
            genreItem(item)
        }
    }
}

@Composable
fun TitleImg(modifier: Modifier, imgURL: String) {
    AsyncImage(
        model = imgURL,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f),
        contentScale = ContentScale.Crop, // Обрезаем верх и низ, заполняя ширину
        contentDescription = null
    )
}

@Composable
fun LargeTextAverageScore(averageScore: Int) {
    Text(
        text = formatAverageScore(averageScore),
        color = if (averageScore >= 70) MyColors.Green else MyColors.Gray,
        style = MaterialTheme.typography.h2
    )
}

//@Composable
//fun CircularBackButton(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
//    Box(modifier = modifier.fillMaxSize()) {
//        IconButton(
//            onClick = onBackClick,
//            modifier = Modifier
//                .align(Alignment.TopStart)
//                .padding(16.dp)
//                .size(48.dp)
//                .clip(CircleShape)
//                .background(MaterialTheme.colors.primary) // Фон кнопки
//        ) {
//            androidx.compose.material3.Icon(
//                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                contentDescription = "Назад",
//                tint = Color.White
//            )
//        }
//    }
//}