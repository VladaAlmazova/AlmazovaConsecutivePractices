package com.example.almazovaconsecutivepractices.listWithDetails.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeShortEntity
import com.example.almazovaconsecutivepractices.data.repository.IAnimeRepository
import com.example.almazovaconsecutivepractices.listWithDetails.presentation.screens.DetailsScreen
import com.example.almazovaconsecutivepractices.listWithDetails.presentation.state.ListState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward

class ListViewModel(
    private val repository: IAnimeRepository,
    private val navigation: StackNavContainer
) : ViewModel() {

    private val mutableState = MutableListState()
    val viewState = mutableState as ListState

    init {
        loadAnimeList()
    }

    private fun loadAnimeList() {
        mutableState.items = repository.getList()
    }

    fun onItemClicked(id: Int) {
        navigation.forward(DetailsScreen(titleId = id))
    }

    private class MutableListState : ListState {
        override var items: List<AnimeShortEntity> by mutableStateOf(emptyList())
        override val isEmpty get() = items.isEmpty()
    }
}