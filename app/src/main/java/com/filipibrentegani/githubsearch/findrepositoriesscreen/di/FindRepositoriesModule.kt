package com.filipibrentegani.githubsearch.findrepositoriesscreen.di

import com.filipibrentegani.githubsearch.findrepositoriesscreen.presentation.FindRepositoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {
    viewModel { FindRepositoriesViewModel() }
}