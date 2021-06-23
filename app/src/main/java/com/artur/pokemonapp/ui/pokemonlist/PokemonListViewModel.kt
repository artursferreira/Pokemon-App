package com.artur.pokemonapp.ui.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.artur.pokemonapp.repository.PokemonRepository
import com.artur.pokemonapp.util.Constants.Companion.UPDATE_POKEMON_WORKER
import com.artur.pokemonapp.worker.UpdatePokemonWorker
import java.util.concurrent.TimeUnit

class PokemonListViewModel(pokemonRepository: PokemonRepository, val workManager: WorkManager) :
    ViewModel() {

    val pokemonLiveData = pokemonRepository.loadPokemons()

    init {
        fetchData()
    }

    private fun fetchData() {
        val updateWorker = PeriodicWorkRequestBuilder<UpdatePokemonWorker>(24, TimeUnit.HOURS)
            .build()

        workManager.enqueueUniquePeriodicWork(
            UPDATE_POKEMON_WORKER,
            ExistingPeriodicWorkPolicy.REPLACE,
            updateWorker
        )

    }

}