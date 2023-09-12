package com.example.pokemons.ui.fragmentMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemons.data.models.Pokemon
import com.example.pokemons.data.models.PokemonResponse
import com.example.pokemons.domain.Repository

class FragmentMainVM(private val repository: Repository): ViewModel(){

    lateinit var data: PokemonResponse

    private val mutablePokemonsList = MutableLiveData<List<Pokemon>>()
    val livePokemonsList: LiveData<List<Pokemon>> = mutablePokemonsList

    private fun setData() {
       data = repository.getPokemonsData()
    }

    fun refreshList(){
        mutablePokemonsList.postValue(data.pokemons)
    }

}