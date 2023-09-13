package com.example.pokemons.data.db

import com.example.pokemons.data.models.Pokemon

interface LocalDb {
    fun saveToDb(list: List<Pokemon>)

    fun getFromDb(): List<Pokemon>

    fun clearDb()

}