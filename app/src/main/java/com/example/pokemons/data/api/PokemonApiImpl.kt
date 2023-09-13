package com.example.pokemons.data.api

import com.example.pokemons.data.models.PokemonDetails
import com.example.pokemons.data.models.PokemonResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PokemonApiImpl(
    private val retrofit: Retrofit
) : PokemonApi {

    lateinit var pokemonResponse: PokemonResponse
    lateinit var pokemon: PokemonDetails
    override fun getData(): PokemonResponse {

        val observable = retrofit.getPokemonResponse()
        val observer = getResponseObserver()
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
        return pokemonResponse
    }

    override fun getPokemon(number: Int): PokemonDetails {
        val observable = retrofit.getPokemon(number.toString())
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
                pokemonResponse = t
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