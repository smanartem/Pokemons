package com.example.pokemons.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.pokemons.data.models.Pokemon

class PokemonDiffUtil: DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.name == newItem.name
    }
}