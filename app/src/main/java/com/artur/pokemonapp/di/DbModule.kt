package com.artur.pokemonapp.di

import androidx.room.Room
import com.artur.pokemonapp.data.local.AppDatabase
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "pokemon-database"
        )
            .build()
    }
    single { get<AppDatabase>().pokemonDao() }
}
