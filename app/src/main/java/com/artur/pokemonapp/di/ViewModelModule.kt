package com.artur.pokemonapp.di

import com.artur.pokemonapp.ui.pokemonlist.PokemonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PokemonListViewModel(pokemonRepository = get()) }
}