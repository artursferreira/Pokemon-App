package com.artur.pokemonapp.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.artur.pokemonapp.repository.PokemonRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpdatePokemonWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params), KoinComponent {

    private val pokemonRepository : PokemonRepository by inject()

    override suspend fun doWork(): Result {
        return try {
            pokemonRepository.loadPokemons()
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}