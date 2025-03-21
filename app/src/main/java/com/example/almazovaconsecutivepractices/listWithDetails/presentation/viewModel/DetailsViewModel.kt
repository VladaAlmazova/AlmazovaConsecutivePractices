package com.example.almazovaconsecutivepractices.listWithDetails.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.almazovaconsecutivepractices.data.domain.entity.AnimeFullEntity
import com.example.almazovaconsecutivepractices.data.repository.IAnimeRepository
import com.example.almazovaconsecutivepractices.listWithDetails.presentation.state.DetailsState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.back

class DetailsViewModel(
    private val repository: IAnimeRepository,
    private val navigation: StackNavContainer,
    private val id: Int
) : ViewModel() {


    private val mutableState = MutableDetailsState()
    val viewState = mutableState as DetailsState

    init {
        mutableState.anime = repository.getById(id)
    }

    fun back() {
        navigation.back()
    }

    fun onRatingChanged(rating: Float) {
        mutableState.rating = rating
    }

    fun onSavedChanged(isSave: Boolean) {
        mutableState.isSavedAnime = isSave
    }

    private class MutableDetailsState : DetailsState {
        override var anime: AnimeFullEntity? by mutableStateOf(null)
        override var rating: Float by mutableFloatStateOf(0f)
        override val isRatingVisible: Boolean get() = rating != 0f
        override var isSavedAnime: Boolean by mutableStateOf(false)
    }
}