package com.artur.pokemonapp.ui.pokemonlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.artur.pokemonapp.R
import com.artur.pokemonapp.data.Result
import com.artur.pokemonapp.data.local.PokemonItem
import com.artur.pokemonapp.databinding.FragmentPokemonListBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonListFragment : Fragment(), PokemonItemAdapter.OnItemClickListener {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    private val adapter = PokemonItemAdapter(this)

    private val viewModel : PokemonListViewModel by viewModel()

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
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers() {
        viewModel.pokemonLiveData.observe(viewLifecycleOwner, {
            when(it.status) {
                Result.Status.LOADING -> {
                    binding.pokemonRecyclerview.visibility = GONE
                    binding.progressCircular.visibility = VISIBLE
                }
                Result.Status.SUCCESS -> {
                    adapter.submitList(it.data)
                    binding.progressCircular.visibility = GONE
                    binding.pokemonRecyclerview.visibility = VISIBLE
                }
                Result.Status.ERROR -> {
                    binding.progressCircular.visibility = GONE
                    Snackbar.make(binding.root, getString(R.string.pokemon_error), Snackbar.LENGTH_LONG).show()
                }
            }
        })
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