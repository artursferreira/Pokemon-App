package com.artur.pokemonapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.artur.pokemonapp.api.PokemonService
import com.artur.pokemonapp.data.Resource
import com.artur.pokemonapp.data.local.PokemonDao
import com.artur.pokemonapp.data.local.PokemonItem
import com.artur.pokemonapp.util.Constants.Companion.POKEMONS
import com.artur.pokemonapp.util.RateLimiter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class PokemonRepository(
    private val pokemonDao: PokemonDao,
    private val service: PokemonService
) {

    private val rateLimit = RateLimiter<String>(5, TimeUnit.MINUTES)

    fun loadPokemons(owner: String = POKEMONS): LiveData<Resource<List<PokemonItem>>> {
        return networkBoundResource(
            query = { pokemonDao.getAll() },
            fetch = { service.getPokemons() },
            shouldFetch = { items -> items.isNullOrEmpty() || rateLimit.shouldFetch(owner) },
            saveFetchResult = { items -> withContext(Dispatchers.IO) {pokemonDao.insertAll(items.pokemons)} },
            onFetchFailed = { rateLimit.reset(owner) }
        ).asLiveData()

    }

}