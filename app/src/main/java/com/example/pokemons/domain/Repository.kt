package com.example.pokemons.domain

import com.example.pokemons.data.models.PokemonResponse

interface Repository {
    fun getPokemon(){

    }

    fun getPokemonsData(): PokemonResponse{

    }
}