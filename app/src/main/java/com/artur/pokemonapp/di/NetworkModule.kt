package com.artur.pokemonapp.di

import com.artur.pokemonapp.api.PokemonService
import com.artur.pokemonapp.util.Constants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit( url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

inline fun <reified T> createWebService(retrofit: Retrofit): T = retrofit.create(T::class.java)

val networkModule = module {
    single { provideRetrofit(Constants.BASE_URL) }

    single { createWebService<PokemonService>(retrofit = get()) }
}