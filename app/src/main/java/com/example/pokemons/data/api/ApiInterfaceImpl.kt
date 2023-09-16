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

    fun getPokemon(number: Int): Observable<PokemonDetails> {
       return api.getPokemon(number.toString())
    }
//
//    private fun getResponseObserver(): Observer<PokemonResponse> {
//        return object : Observer<PokemonResponse> {
//            override fun onSubscribe(d: Disposable) = Unit
//
//            override fun onNext(t: PokemonResponse) {
//
//            }
//
//            override fun onError(e: Throwable) = Unit
//
//            override fun onComplete() = Unit
//        }
//    }

//    private fun getPokemonObserver(): Observer<PokemonDetails> {
//        return object : Observer<PokemonDetails> {
//            override fun onSubscribe(d: Disposable) = Unit
//
//            override fun onNext(t: PokemonDetails) {
//                pokemon = t
//            }
//
//            override fun onError(e: Throwable) = Unit
//
//            override fun onComplete() = Unit
//        }
//    }
}