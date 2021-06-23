package com.artur.pokemonapp.api

import retrofit2.http.GET

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemons(): PokemonResponse

}