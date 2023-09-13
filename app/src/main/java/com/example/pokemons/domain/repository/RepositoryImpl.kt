package com.example.pokemons.domain.repository

import android.content.Context
import com.example.pokemons.data.api.PokemonApiImpl
import com.example.pokemons.data.db.LocalDbImpl
import com.example.pokemons.data.models.Pokemon
import com.example.pokemons.data.models.PokemonDetails
import com.example.pokemons.utils.LIMIT_ON_PAGE
import com.example.pokemons.utils.isInternetAvailable

class RepositoryImpl(
    private val api: PokemonApiImpl,
    private val db: LocalDbImpl,
    private val context: Context
) : Repository {

    private var pokemonList: List<Pokemon>

    init {
        refreshDb()
        pokemonList = db.getFromDb()
    }

    private fun refreshDb(){
        if(isInternetAvailable(context)){
            val apiData = api.getData()
            db.saveToDb(apiData.pokemons)
        }
    }

    override fun getPokemon(number: Int): PokemonDetails {
        //TODO: add check for internet connection
        return api.getPokemon(number)
    }

    override fun getPokemonsPage(page: Int): List<Pokemon> {
        return pokemonList.subList(page, page + LIMIT_ON_PAGE)
    }
}