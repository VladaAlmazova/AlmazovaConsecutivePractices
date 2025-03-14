package com.example.almazovaconsecutivepractices.listWithDetails.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeFullEntity
import com.example.almazovaconsecutivepractices.data.domain.entity.Tag
import com.example.almazovaconsecutivepractices.data.repository.AnimeRepository
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.back
import kotlinx.parcelize.Parcelize
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.flow.merge

@Parcelize
class DetailsScreen(
    val titleId: Int, override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @Composable
    override fun Content(modifier: Modifier) {
        val stackNavigation = LocalStackNavigation.current
        Column(modifier = Modifier) {
            UpToolbar(title = " ", onBackClick = { stackNavigation.back() })

            val title: AnimeFullEntity? = AnimeRepository().getById(titleId)
            if (title == null) Text(
                text = "Тайтл не найден", modifier = modifier.fillMaxSize(), fontSize = 40.sp
            )
            else {
                Text(
                    text = title.title, modifier = modifier.fillMaxSize(), fontSize = 40.sp
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpToolbar(title: String, onBackClick: () -> Unit) {
    TopAppBar(title = { Text(title) }, navigationIcon = {
        IconButton(onClick = onBackClick) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
        }
    })
}

@Composable
fun ConstraintLayoutContent() {
    val animeFullEntity = AnimeRepository().getById(1)
    if (animeFullEntity == null) return

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

        var myScore = 0f

        SliderRating(modifier = Modifier.constrainAs(myScoreRef) {
                top.linkTo(
                    anchor = scoreRef.bottom, margin = 16.dp
                )
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, rating = myScore, onRatingChanged = { newRating -> myScore = newRating })
    }
}

@Preview
@Composable
fun prevDetails() {
    ConstraintLayoutContent()
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

//@Preview
//@Composable
//fun prevScrollableGenres(){
//    ScrollableGenres(AnimeRepository().getById(1)!!.genres)
//}

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

//@Preview
//@Composable
//fun prevTitleImg(){
//    TitleImg(AnimeRepository().getById(1)!!.coverImageExtraLargeUrl);
//}


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
    modifier: Modifier, rating: Float, // Текущий рейтинг (0-10)
    onRatingChanged: (Float) -> Unit // Колбек при изменении рейтинга
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Slider(
            value = rating,
            onValueChange = { newValue -> onRatingChanged(newValue) },
            valueRange = 0f..10f, // Диапазон от 0 до 10
            steps = 9, // Делаем шаг 1.0 (чтобы было 10 делений)
            modifier = modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "Рейтинг: ${"%.1f".format(rating)}/10",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}