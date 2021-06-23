package com.artur.pokemonapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PokemonItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}