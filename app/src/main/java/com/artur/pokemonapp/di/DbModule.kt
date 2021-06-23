package com.artur.pokemonapp.di

import androidx.room.Room
import com.artur.pokemonapp.data.local.AppDatabase
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
