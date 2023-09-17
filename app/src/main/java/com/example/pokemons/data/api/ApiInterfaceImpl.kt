package com.example.pokemons.data.api

import com.example.pokemons.data.models.PokemonDetails
import com.example.pokemons.data.models.PokemonResponse
import io.reactivex.Observable
import javax.inject.Inject

class ApiInterfaceImpl @Inject constructor(
    private val api: PokemonsApi
) {

    fun getPokemonList(): Observable<PokemonResponse> {
        return api.getPokemonResponse()
    }

    fun getPokemon(path: String): Observable<PokemonDetails> {
       return api.getPokemon(path)
    }
}