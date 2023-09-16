package com.example.pokemons.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokemons.utils.DB_NAME

@Entity(tableName = DB_NAME)
data class Pokemon(
    @PrimaryKey
    @ColumnInfo
val name: String,
    @ColumnInfo
val url: String
)