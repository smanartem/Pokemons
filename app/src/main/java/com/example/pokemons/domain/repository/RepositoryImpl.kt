package com.example.pokemons.domain.repository

import com.example.pokemons.data.db.LocalDb
import com.example.pokemons.data.api.PokemonApi

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