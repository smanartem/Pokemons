package com.example.pokemons.ui.fragmentDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemons.data.models.PokemonDetails
import com.example.pokemons.domain.repository.RepositoryImpl
import javax.inject.Inject

class FragmentDetailsVM @Inject constructor(private val repository: RepositoryImpl) : ViewModel() {

    //var pokemon: PokemonDetails

    private val mutablePokemon = MutableLiveData<PokemonDetails>()
    val livePokemon: LiveData<PokemonDetails> = mutablePokemon

    fun downloadPokemon(number: Int) {
        mutablePokemon.postValue(repository.getPokemon(number))
    }

}