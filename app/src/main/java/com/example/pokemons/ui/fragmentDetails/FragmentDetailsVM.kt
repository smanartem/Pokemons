package com.example.pokemons.ui.fragmentDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemons.data.models.Pokemon
import com.example.pokemons.domain.Repository

class FragmentDetailsVM(private val repository: Repository): ViewModel() {

    var pokemon: Pokemon

    private val mutablePokemon = MutableLiveData<Pokemon>()
    val livePokemon: LiveData<Pokemon> = mutablePokemon

    init {
        pokemon = repository.getPokemon()
        refreshPokemon()
    }

    fun refreshPokemon(){
        mutablePokemon.postValue(pokemon)
    }
}