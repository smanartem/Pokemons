package com.example.pokemons.ui.fragmentDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemons.data.models.PokemonDetails
import com.example.pokemons.domain.repository.Repository
import javax.inject.Inject

class FragmentDetailsVM @Inject constructor(private val repository: Repository) : ViewModel() {

    //var pokemon: PokemonDetails

    private val mutablePokemon = MutableLiveData<PokemonDetails>()
    val livePokemon: LiveData<PokemonDetails> = mutablePokemon

    fun downloadPokemon(number: Int) {
        mutablePokemon.postValue(repository.getPokemon(number))
    }

}