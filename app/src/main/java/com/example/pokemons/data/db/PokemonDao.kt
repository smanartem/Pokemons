package com.example.pokemons.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokemons.data.models.Pokemon
import com.example.pokemons.utils.DB_NAME
import io.reactivex.Observable

@Dao
interface PokemonDao {
    @Query("SELECT * FROM $DB_NAME")
    fun getPokemonsList(): Observable<List<Pokemon>>

    @Query("DELETE FROM $DB_NAME")
    fun clearDB()

    @Insert
    fun addListToDB(list: List<Pokemon>)
}