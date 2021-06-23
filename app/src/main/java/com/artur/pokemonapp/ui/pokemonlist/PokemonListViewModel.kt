package com.artur.pokemonapp.ui.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.artur.pokemonapp.repository.PokemonRepository
import com.artur.pokemonapp.worker.UpdatePokemonWorker
import java.util.concurrent.TimeUnit

class PokemonListViewModel(pokemonRepository: PokemonRepository, val workManager: WorkManager) :
    ViewModel() {

    val pokemonLiveData = pokemonRepository.loadPokemons()

    init {
        fetchData()
    }

    fun fetchData() {
        val updateWorker = PeriodicWorkRequestBuilder<UpdatePokemonWorker>(1, TimeUnit.DAYS)
            .build()

        workManager.enqueue(updateWorker)

    }

}