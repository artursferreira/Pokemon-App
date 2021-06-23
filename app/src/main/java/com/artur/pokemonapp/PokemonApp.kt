package com.artur.pokemonapp

import android.app.Application
import com.artur.pokemonapp.di.dbModule
import com.artur.pokemonapp.di.pokemonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokemonApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(dbModule, pokemonModule)
        }
    }
}