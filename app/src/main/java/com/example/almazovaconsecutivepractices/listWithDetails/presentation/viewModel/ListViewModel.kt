package com.example.almazovaconsecutivepractices.listWithDetails.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeShortEntity
import com.example.almazovaconsecutivepractices.data.repository.IAnimeRepository
import com.example.almazovaconsecutivepractices.listWithDetails.presentation.screens.DetailsScreen
import com.example.almazovaconsecutivepractices.listWithDetails.presentation.state.ListState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: IAnimeRepository,
    private val navigation: StackNavContainer
) : ViewModel() {

    private val mutableState = MutableListState()
    val viewState = mutableState as ListState

    private var isLoading = false

    init {
        loadAnimeList()
    }

    fun loadInitialData() {
        loadAnimeList(loadMore = false)
    }

    private fun loadAnimeList(loadMore: Boolean = false) {
        if (isLoading) return

        viewModelScope.launch {
            isLoading = true
            try {
                val newItems = repository.getList(loadMore)

                mutableState.items = if (loadMore) {
                    mutableState.items + newItems
                } else {
                    newItems
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }

//        mutableState.items = repository.getList()
    }

    fun onItemClicked(id: Int) {
        navigation.forward(DetailsScreen(titleId = id))
    }

    fun onLoadMoreRequested() {
        loadAnimeList(loadMore = true)
    }

    private class MutableListState : ListState {
        override var items: List<AnimeShortEntity> by mutableStateOf(emptyList())
        override val isEmpty get() = items.isEmpty()
    }
}