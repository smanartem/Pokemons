package com.example.pokemons.domain.repository

import com.example.pokemons.data.api.PokemonApiImpl
import com.example.pokemons.data.db.LocalDbImpl
import com.example.pokemons.data.models.Pokemon

class RepositoryImpl(
    private val api: PokemonApiImpl,
    private val db: LocalDbImpl
): Repository {

    private var pokemonList: List<Pokemon>

    init {
        val apiData = api.getData()?: emptyList<Pokemon>()
        if(apiData != null) db.saveToDb(apiData)

        val dbData = db.getFromDb()
        if(dbData.isNotEmpty()){
            pokemonList = dbData
        }else{
            showEmptyFragment()
        }
    }

    override fun getPokemon() {
        //TODO: add check for internet connection
        return api.getPokemon()
    }

    override fun getPokemonsData(): List<Pokemon> {
        return pokemonList
    }
}