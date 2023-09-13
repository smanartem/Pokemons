package com.example.pokemons.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.pokemons.utils.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class PokemonEntity(
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val url: String
)