package com.example.pokemons.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomMasterTable.TABLE_NAME
import com.example.pokemons.data.models.Pokemon

@Dao
interface PokemonDao {
    @Query("SELECT * FROM $TABLE_NAME")
    fun getPokemonsList(): List<Pokemon>

    @Query("DELETE FROM $TABLE_NAME")
    fun clearDB()

    @Insert
    fun addListToDB(list: List<Pokemon>)
}