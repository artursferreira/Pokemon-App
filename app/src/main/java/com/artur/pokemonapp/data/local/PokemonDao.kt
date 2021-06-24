package com.artur.pokemonapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun findById(id: String): LiveData<PokemonItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemon: PokemonItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pokemonList: List<PokemonItem>)

    @Query("SELECT * FROM pokemon")
    fun getAll(): LiveData<List<PokemonItem>>

}