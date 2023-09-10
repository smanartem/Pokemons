package com.example.pokemons.data.models

data class Response(
    val count: Int,
    val next: String,
    val previous: Any,
    val pokemons: List<Result>
)