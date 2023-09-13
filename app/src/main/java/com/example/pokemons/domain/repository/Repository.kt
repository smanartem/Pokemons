package com.example.pokemons.domain.repository

import com.example.pokemons.data.models.Pokemon
import com.example.pokemons.data.models.PokemonDetails

interface Repository {
    fun getPokemon(number: Int): PokemonDetails

    fun getPokemonsPage(page: Int): List<Pokemon>

}