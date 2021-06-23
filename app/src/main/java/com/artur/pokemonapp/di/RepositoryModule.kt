package com.artur.pokemonapp.di

import com.artur.pokemonapp.api.PokemonService
import com.artur.pokemonapp.repository.PokemonRepository
import com.artur.pokemonapp.ui.pokemonlist.PokemonListViewModel
import com.artur.pokemonapp.util.Constants.Companion.BASE_URL
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {

    single {
        PokemonRepository(
            pokemonDao = get(),
            service = get()
        )
    }

}