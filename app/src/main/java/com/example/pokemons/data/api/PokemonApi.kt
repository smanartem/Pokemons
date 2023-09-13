package com.example.pokemons.data.api

import com.example.pokemons.data.models.PokemonDetails
import com.example.pokemons.data.models.PokemonResponse

interface PokemonApi {

    fun getData(): PokemonResponse

    fun getPokemon(number: Int): PokemonDetails

}