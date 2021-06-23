package com.artur.pokemonapp.repository

import androidx.lifecycle.LiveData
import com.artur.pokemonapp.api.PokemonResponse
import com.artur.pokemonapp.api.PokemonService
import com.artur.pokemonapp.data.Result
import com.artur.pokemonapp.data.getResult
import com.artur.pokemonapp.data.local.PokemonDao
import com.artur.pokemonapp.data.local.PokemonItem
import com.artur.pokemonapp.data.resultLiveData
import com.artur.pokemonapp.util.Constants.Companion.POKEMONS

class PokemonRepository(
    private val pokemonDao: PokemonDao,
    private val service: PokemonService
) {

    fun loadPokemons(owner: String = POKEMONS): LiveData<Result<List<PokemonItem>>> {
        return resultLiveData(
            databaseQuery = { pokemonDao.getAll() },
            networkCall = { getResult { service.getPokemons() } },
            saveCallResult = { pokemonResponse: PokemonResponse ->
                pokemonDao.insertAll(
                    pokemonResponse.pokemons
                )
            })

    }

}