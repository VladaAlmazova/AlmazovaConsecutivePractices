package com.example.almazovaconsecutivepractices.listWithDetails.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeShortEntity
import com.example.almazovaconsecutivepractices.listWithDetails.presentation.viewModel.ListViewModel
import com.example.almazovaconsecutivepractices.listWithDetails.presentation.viewModel.MapperAnime.formatAverageScore
import com.example.almazovaconsecutivepractices.ui.theme.Spacing
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

//11,48

/**
 * UI экрана со списком аниме
 */
@Composable
fun ListAnimeContent(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Список Аниме") },
            )
        },
        content = { paddingValues ->
            val stackNavigation = LocalStackNavigation.current
            val viewModel = koinViewModel<ListViewModel> { parametersOf(stackNavigation) }
            val state = viewModel.viewState

            if (state.isEmpty) {
                Text("Информация не найдена")
            }

            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = Spacing.medium)
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
    )
}

/**
 * Реализация Screen
 */
@Parcelize
class ListScreen(override val screenKey: ScreenKey = generateScreenKey()) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {
        ListAnimeContent(modifier)
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
            .padding(Spacing.extraSmall)
            .clickable { openNextScreen() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.small),
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
            text = item.formatAverageScore()
        )
    }
}

