package com.example.pokemons.data.api

import com.example.pokemons.data.models.PokemonDetails
import com.example.pokemons.data.models.PokemonResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonsApi {
    @GET("https://pokeapi.co/api/v2/pokemon/?limit=100&offset=0")
    fun getPokemonResponse(): Observable<PokemonResponse>

    @GET("{path}")
    fun getPokemon(
        @Path(
            value = "path",
            encoded = true
        ) number: String
    ): Observable<PokemonDetails>

}