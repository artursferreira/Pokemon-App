package com.artur.pokemonapp.ui.pokemonlist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.artur.pokemonapp.data.local.PokemonItem
import com.artur.pokemonapp.databinding.PokemonItemBinding
import com.bumptech.glide.Glide

class PokemonItemAdapter(
    private val itemClickListener: OnItemClickListener
) : ListAdapter<PokemonItem, PokemonItemAdapter.ViewHolder>(object :
    DiffUtil.ItemCallback<PokemonItem>() {
    override fun areItemsTheSame(oldItem: PokemonItem, newItem: PokemonItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PokemonItem, newItem: PokemonItem): Boolean {
        return oldItem == newItem
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = PokemonItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        val holder = ViewHolder(itemBinding)

        holder.itemView.setOnClickListener { itemClickListener.onItemClicked(currentList[holder.absoluteAdapterPosition]) }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: PokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: PokemonItem) {
            with(binding) {
                pokemonName.text = pokemon.name

                Glide.with(pokemonImage)
                    .load(pokemon.imagePath)
                    .into(pokemonImage)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClicked(pokemon: PokemonItem)
    }

}