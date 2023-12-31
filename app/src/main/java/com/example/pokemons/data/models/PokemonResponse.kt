package com.example.pokemons.data.models

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Pokemon>
)