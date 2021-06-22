package com.artur.pokemonapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonItem(val id: String, val name: String, val imagePath: String) : Parcelable