package com.artur.pokemonapp.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "pokemon")
data class PokemonItem(
    @PrimaryKey val id: String,
    val name: String,
    val imagePath: String
) : Parcelable