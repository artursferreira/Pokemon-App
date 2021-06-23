package com.artur.pokemonapp.api

import retrofit2.Response
import retrofit2.http.GET

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemons(): Response<PokemonResponse>

}