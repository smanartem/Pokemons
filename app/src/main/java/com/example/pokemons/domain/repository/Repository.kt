package com.example.pokemons.domain.repository

import com.example.pokemons.data.models.Pokemon
import com.example.pokemons.data.models.PokemonResponse

interface Repository {
    fun getPokemon(): Pokemon {

    }

    fun getPokemonsData(): PokemonResponse{

    }
}