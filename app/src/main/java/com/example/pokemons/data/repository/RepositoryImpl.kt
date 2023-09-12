package com.example.pokemons.data.repository

import com.example.pokemons.domain.LocalDb
import com.example.pokemons.domain.PokemonApi
import com.example.pokemons.domain.Repository

class RepositoryImpl(
    api: PokemonApi,
    db: LocalDb
): Repository {
    override fun getPokemon() {
        super.getPokemon()
    }

    override fun getPokemonsData() {
        super.getPokemonsData()
    }
}