package com.artur.pokemonapp.util

import com.artur.pokemonapp.data.local.PokemonItem

object TestUtil {

    fun createPokemon(id: String = "001", name: String = "name", imagePath: String = "url") =
        PokemonItem(id = id, name, imagePath)

    fun createPokemonList(
        count: Int
    ): List<PokemonItem> {
        return (0 until count).map {
            createPokemon(
                id = "00$it"
            )
        }
    }
}