package com.example.pokemons.data.db

import com.example.pokemons.data.models.Pokemon
import io.reactivex.Observable

interface LocalDb {
    fun saveToDb(list: List<Pokemon>)

    fun getFromDb(): Observable<List<Pokemon>>

    fun clearDb()

}