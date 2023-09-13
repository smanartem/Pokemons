package com.example.pokemons.ui.fragmentMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemons.data.models.Pokemon
import com.example.pokemons.domain.repository.Repository
import com.example.pokemons.utils.START_PAGE

class FragmentMainVM(private val repository: Repository) : ViewModel() {

    var data: List<Pokemon> = repository.getPokemonsPage(START_PAGE)

    private val mutablePokemonsList = MutableLiveData<List<Pokemon>>()
    val livePokemonsList: LiveData<List<Pokemon>> = mutablePokemonsList

    init {
        refreshList()
    }

    private fun refreshList() {
        mutablePokemonsList.postValue(data)
    }

}