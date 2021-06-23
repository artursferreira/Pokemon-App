package com.artur.pokemonapp.pokemonlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.artur.pokemonapp.data.PokemonItem
import com.artur.pokemonapp.databinding.FragmentPokemonListBinding

class PokemonListFragment : Fragment(), PokemonItemAdapter.OnItemClickListener {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    private val adapter = PokemonItemAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pokemonRecyclerview.adapter = adapter
        val list = mutableListOf<PokemonItem>()
        list.add(PokemonItem("001", "Bulbasaur", "https://img.pokemondb.net/artwork/bulbasaur.jpg"))
        list.add(PokemonItem("002", "Ivysaur", "https://img.pokemondb.net/artwork/ivysaur.jpg"))
        list.add(PokemonItem("003", "Venusaur", "https://img.pokemondb.net/artwork/venusaur.jpg"))
        adapter.submitList(list)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(pokemon: PokemonItem) {
        val action =
            PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(
                pokemon = pokemon,
                title = pokemon.name
            )
        findNavController().navigate(action)
    }

}