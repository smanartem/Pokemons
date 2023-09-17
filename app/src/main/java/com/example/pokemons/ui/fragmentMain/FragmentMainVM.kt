package com.example.pokemons.ui.fragmentMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pokemons.data.models.Pokemon
import com.example.pokemons.domain.repository.Repository
import javax.inject.Inject

class FragmentMainVM @Inject constructor(private val repository: Repository) : ViewModel() {

    val pokemonListLive: LiveData<List<Pokemon>> = repository.pokemonList

}