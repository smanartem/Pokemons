package com.example.pokemons.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokemons.utils.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class PokemonEntity(
    @PrimaryKey
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val url: String
)