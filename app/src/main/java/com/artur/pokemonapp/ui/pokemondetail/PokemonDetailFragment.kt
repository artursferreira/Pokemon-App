package com.artur.pokemonapp.ui.pokemondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.artur.pokemonapp.data.local.PokemonItem
import com.artur.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.bumptech.glide.Glide

class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<PokemonDetailFragmentArgs>()
    private val pokemonItem : PokemonItem by lazy { args.pokemon }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        Glide.with(this)
            .load(pokemonItem.imagePath)
            .into(binding.pokemonImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}