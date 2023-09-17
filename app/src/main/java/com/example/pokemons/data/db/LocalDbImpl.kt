package com.example.pokemons.data.db

import com.example.pokemons.data.models.Pokemon
import io.reactivex.Observable
import javax.inject.Inject

class LocalDbImpl @Inject constructor(private val dao: PokemonDao): LocalDb {
    override fun saveToDb(list: List<Pokemon>) {
        clearDb()
        dao.addListToDB(list)
    }

    override fun getFromDb() : Observable<List<Pokemon>> {
        return dao.getPokemonsList()
    }

    override fun clearDb() {
        dao.clearDB()
    }

}