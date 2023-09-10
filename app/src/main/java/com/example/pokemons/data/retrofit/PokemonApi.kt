package com.example.pokemons.data.retrofit

import com.example.pokemons.data.models.PokemonDetails
import com.example.pokemons.data.models.PokemonResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("https://pokeapi.co/api/v2/pokemon/?limit=20&offset={offset}")
    fun getPokemonsList(@Path(value = "offset", encoded = true) offset: String) : Observable<PokemonResponse>

    @GET("https://pokeapi.co/api/v2/pokemon/{number}/")
    fun getPokemon(@Path(value = "number", encoded = true) number: String) : Observable<PokemonDetails>

}