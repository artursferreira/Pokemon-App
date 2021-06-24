package com.artur.pokemonapp.di

import androidx.work.WorkManager
import com.artur.pokemonapp.ui.pokemonlist.PokemonListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    factory { WorkManager.getInstance(androidContext()) }

    viewModel { PokemonListViewModel(pokemonRepository = get(), workManager = get()) }
}