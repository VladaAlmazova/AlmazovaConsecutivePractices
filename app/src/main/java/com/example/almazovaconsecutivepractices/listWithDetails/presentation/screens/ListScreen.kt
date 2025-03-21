package com.example.almazovaconsecutivepractices.listWithDetails.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeShortEntity
import com.example.almazovaconsecutivepractices.listWithDetails.presentation.viewModel.ListViewModel
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Parcelize
class ListScreen(override val screenKey: ScreenKey = generateScreenKey()) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {
        val stackNavigation = LocalStackNavigation.current
        val viewModel = koinViewModel<ListViewModel> { parametersOf(stackNavigation) }
        val state = viewModel.viewState

        if (state.isEmpty) {
            Text("Информация не найдена")
        }

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 24.dp, start = 10.dp, end = 10.dp)
        )
        {
            items(viewModel.viewState.items) { item ->
                AnimeItem(
                    item = item,
                    openNextScreen = {
                        viewModel.onItemClicked(item.id)
                    }
                )
            }
        }
    }
}

/**
 * Элемент списка Аниме
 */
@Composable
fun AnimeItem(
    item: AnimeShortEntity,
    openNextScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { openNextScreen() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
    ) {
        AsyncImage(
            model = item.coverImageMediumUrl,
            contentDescription = null
        )
        Text(
            text = item.title,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = textAverageScore(item.averageScore),
        )
    }
}

/**
 * Возвращает текстовое представление средней оценки тайтла
 */
fun textAverageScore(averageScore: Int): String {
    return (averageScore.toFloat() / 10).toString()
}
