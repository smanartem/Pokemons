package com.example.pokemons.ui.fragmentDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pokemons.data.models.PokemonDetails
import com.example.pokemons.domain.repository.Repository
import javax.inject.Inject

class FragmentDetailsVM @Inject constructor(private val repository: Repository) : ViewModel() {
    private var pokemonPath = ""

    val pokemonLive: LiveData<PokemonDetails>
        get() = repository.pokemon

    init {
        repository.loadPokemon(pokemonPath)
    }

    fun setPokemonNumber(path: String) {
        pokemonPath = path
        repository.loadPokemon(path)
    }
}