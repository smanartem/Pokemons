package com.example.pokemons.data.db

import com.example.pokemons.data.models.Pokemon
import javax.inject.Inject

class LocalDbImpl @Inject constructor(private val dao: PokemonDao): LocalDb {
    override fun saveToDb(list: List<Pokemon>) {
        dao.addListToDB(list)
    }

    override fun getFromDb() : List<Pokemon>{
        return dao.getPokemonsList()
    }

    override fun clearDb() {
        dao.clearDB()
    }
}