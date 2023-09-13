package com.example.pokemons.ui.fragmentDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemons.data.models.Pokemon
import com.example.pokemons.data.models.PokemonDetails
import com.example.pokemons.domain.repository.Repository

class FragmentDetailsVM(private val repository: Repository): ViewModel() {

    var pokemon: PokemonDetails

    private val mutablePokemon = MutableLiveData<PokemonDetails>()
    val livePokemon: LiveData<PokemonDetails> = mutablePokemon

    init {
        pokemon = repository.getPokemon()
        refreshPokemon()
    }

    fun refreshPokemon(){
        mutablePokemon.postValue(pokemon)
    }
}