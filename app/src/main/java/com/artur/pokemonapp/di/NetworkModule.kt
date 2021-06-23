package com.artur.pokemonapp.di

import com.artur.pokemonapp.BuildConfig
import com.artur.pokemonapp.api.PokemonService
import com.artur.pokemonapp.util.Constants
import com.artur.pokemonapp.util.NetworkInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

inline fun <reified T> createWebService(retrofit: Retrofit): T = retrofit.create(T::class.java)

val networkModule = module {
    factory { NetworkInterceptor() }
    factory { providesHttplogging() }
    factory { providesOkHttpClient(get(), get()) }
    single { provideRetrofit(okHttpClient = get(), url = Constants.BASE_URL) }

    single { createWebService<PokemonService>(retrofit = get()) }
}

fun providesHttplogging(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor.Level.BODY
    else
        HttpLoggingInterceptor.Level.NONE
    return interceptor
}

fun providesOkHttpClient(
    networkInterceptor: NetworkInterceptor,
    httpLoggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(networkInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}
