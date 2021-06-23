package com.artur.pokemonapp.ui.pokemonlist

import androidx.lifecycle.ViewModel
import com.artur.pokemonapp.repository.PokemonRepository

class PokemonListViewModel(pokemonRepository: PokemonRepository) : ViewModel() {

    val pokemonLiveData = pokemonRepository.loadPokemons()

}