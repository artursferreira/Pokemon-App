package com.artur.pokemonapp.api

import com.artur.pokemonapp.data.local.PokemonItem

data class PokemonResponse (
    val pokemons: List<PokemonItem>
)