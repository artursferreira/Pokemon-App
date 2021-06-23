package com.artur.pokemonapp.di

import androidx.room.Room
import com.artur.pokemonapp.data.local.AppDatabase
import okhttp3.OkHttpClient
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
