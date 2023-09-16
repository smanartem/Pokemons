package com.example.pokemons.domain.repository

import android.content.Context
import com.example.pokemons.data.api.ApiInterfaceImpl
import com.example.pokemons.data.db.LocalDbImpl
import com.example.pokemons.data.models.Pokemon
import com.example.pokemons.data.models.PokemonDetails
import com.example.pokemons.utils.LIMIT_ON_PAGE
import com.example.pokemons.utils.isInternetAvailable

class RepositoryImpl(
    private val api: ApiInterfaceImpl,
    private val db: LocalDbImpl,
    private val context: Context
) : Repository {

    //TODO: if database is empty
    private var pokemonList: List<Pokemon>

    init {
        refreshDb()
        //pokemonList = db.getFromDb()
        pokemonList = api.getPokemonList().pokemons
    }

    private fun refreshDb(){
        if(isInternetAvailable(context)){
            val apiData = api.getPokemonList()
            db.saveToDb(apiData.pokemons)
        }
    }

    override fun getPokemon(number: Int): PokemonDetails {
        return api.getPokemon(number)
    }

    override fun getPokemonsPage(page: Int): List<Pokemon> {
        return pokemonList.subList(page, page + LIMIT_ON_PAGE)
    }
}