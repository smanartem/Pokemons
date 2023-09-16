package com.example.pokemons.data.api

import com.example.pokemons.data.models.PokemonDetails
import com.example.pokemons.data.models.PokemonResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ApiInterfaceImpl @Inject constructor(
    private val api: PokemonsApi
) {
    lateinit var pokemonData: PokemonResponse
    lateinit var pokemon: PokemonDetails

    fun getPokemonList(): PokemonResponse {
        val observable = api.getPokemonResponse()
        val observer = getResponseObserver()
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
        return pokemonData
    }

    fun getPokemon(number: Int): PokemonDetails {
        val observable = api.getPokemon(number.toString())
        val observer = getPokemonObserver()
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
        return pokemon
    }

    private fun getResponseObserver(): Observer<PokemonResponse> {
        return object : Observer<PokemonResponse> {
            override fun onSubscribe(d: Disposable) = Unit

            override fun onNext(t: PokemonResponse) {
                pokemonData = t
            }

            override fun onError(e: Throwable) = Unit

            override fun onComplete() = Unit
        }
    }

    private fun getPokemonObserver(): Observer<PokemonDetails> {
        return object : Observer<PokemonDetails> {
            override fun onSubscribe(d: Disposable) = Unit

            override fun onNext(t: PokemonDetails) {
                pokemon = t
            }

            override fun onError(e: Throwable) = Unit

            override fun onComplete() = Unit
        }
    }
}