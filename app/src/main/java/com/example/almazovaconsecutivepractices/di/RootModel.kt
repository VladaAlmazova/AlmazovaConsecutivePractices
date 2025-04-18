package com.example.almazovaconsecutivepractices.di

import com.example.almazovaconsecutivepractices.data.repository.AnimeRepository
import com.example.almazovaconsecutivepractices.data.repository.IAnimeRepository
import com.example.almazovaconsecutivepractices.listWithDetails.presentation.viewModel.DetailsViewModel
import com.example.almazovaconsecutivepractices.listWithDetails.presentation.viewModel.ListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {
    single<IAnimeRepository> { AnimeRepository() }

    viewModel { ListViewModel(get(), it.get()) }
    viewModel { DetailsViewModel(get(), it.get(), it.get()) }
}