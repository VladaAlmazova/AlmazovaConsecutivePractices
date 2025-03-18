package com.example.almazovaconsecutivepractices.listWithDetails.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.AsyncImage
import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeFullEntity
import com.example.almazovaconsecutivepractices.data.repository.AnimeRepository
import com.example.almazovaconsecutivepractices.screens.CircularBackButton
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.back
import kotlinx.parcelize.Parcelize

@Parcelize
class DetailsScreen(
    val titleId: Int, override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @Composable
    override fun Content(modifier: Modifier) {
        val stackNavigation = LocalStackNavigation.current
        val anime = AnimeRepository().getById(titleId)

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (backRef) = createRefs()

            if (anime == null) {
                Text(
                    text = "Аниме не найдено",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            } else {
                ConstraintLayoutContent(anime)
            }

            CircularBackButton(
                onBackClick = { stackNavigation.back() },
                modifier = Modifier.constrainAs(backRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })
        }
    }
}



@Composable
fun ConstraintLayoutContent(animeFullEntity: AnimeFullEntity) {
    var myScore by remember { mutableStateOf(0f) }  // Добавляем состояние

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
                    anchor = imgRef.bottom, margin = 16.dp
                )
                start.linkTo(parent.start)
            }
            .padding(horizontal = 16.dp), fontSize = 24.sp, fontWeight = FontWeight.Bold)

        SaveToCollectionButton(modifier = Modifier.constrainAs(saveRef) {
            bottom.linkTo(anchor = imgRef.bottom, margin = 5.dp)
            end.linkTo(
                anchor = parent.end, margin = 16.dp
            )
        })

        ScrollableGenres(items = animeFullEntity.genres,
            modifier = Modifier.constrainAs(genresRef) {
                top.linkTo(
                    anchor = titleRef.bottom, margin = 16.dp
                )
            })

        Box(modifier = Modifier.constrainAs(scoreRef) {
            top.linkTo(
                anchor = genresRef.bottom, margin = 16.dp
            )
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            LargeTextAverageScore(animeFullEntity.averageScore)
        }

        SliderRating(modifier = Modifier.constrainAs(myScoreRef) {
            top.linkTo(scoreRef.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, rating = myScore, onRatingChanged = { newRating -> myScore = newRating })
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
            .background(Color(0xFF474389), shape = RoundedCornerShape(50.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = genre, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold
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
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start),
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
        text = (averageScore.toFloat() / 10).toString(),
        color = if (averageScore >= 70) Color(0xFF007D34) else Color.Gray,
        fontSize = 52.sp,
        fontWeight = FontWeight.ExtraBold
    )
}

@Composable
fun SliderRating(
    modifier: Modifier, rating: Float,
    onRatingChanged: (Float) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = rating,
            onValueChange = { newValue -> onRatingChanged(newValue) },
            valueRange = 0f..10f,
            steps = 9,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "Рейтинг: ${"%.1f".format(rating)}/10",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}