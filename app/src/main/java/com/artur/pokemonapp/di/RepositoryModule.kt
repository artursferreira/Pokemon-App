package com.artur.pokemonapp.di

import com.artur.pokemonapp.repository.PokemonRepository
import org.koin.dsl.module

val repositoryModule = module {

    single {
        PokemonRepository(
            pokemonDao = get(),
            service = get()
        )
    }

}