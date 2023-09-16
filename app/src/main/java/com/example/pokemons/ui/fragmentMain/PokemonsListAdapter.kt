package com.example.pokemons.ui.fragmentMain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemons.R
import com.example.pokemons.data.models.Pokemon
import com.example.pokemons.utils.PokemonDiffUtil

class PokemonsListAdapter(
    private val onClickListener: (String) -> Unit) :
    ListAdapter<Pokemon, PokemonsListAdapter.PokemonsViewHolder>(PokemonDiffUtil()) {

    lateinit var pokemonsList: List<Pokemon>

    inner class PokemonsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            itemView.setOnClickListener {
                onClickListener(pokemonsList[bindingAdapterPosition].url)
            }
        }

        fun bindTo(pokemon: Pokemon) {
            //TODO: why binding dont work here?
            val view = itemView.findViewById<TextView>(R.id.pokemonName)
            view.text = pokemon.name
            //itemView.pokemonName.text = pokemon.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsViewHolder {
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonsViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: PokemonsViewHolder, position: Int) {
        holder.bindTo(pokemonsList[position])
    }
}