package com.example.pokemons.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokemons.data.api.ApiInterfaceImpl
import com.example.pokemons.data.db.LocalDbImpl
import com.example.pokemons.data.models.Pokemon
import com.example.pokemons.data.models.PokemonDetails
import com.example.pokemons.data.models.PokemonResponse
import com.example.pokemons.utils.ERROR_NOT_DOWNLOADED
import com.example.pokemons.utils.LIMIT_ON_PAGE
import com.example.pokemons.utils.isInternetAvailable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: ApiInterfaceImpl,
    private val db: LocalDbImpl,
    private val context: Context
) : Repository {

    init {
        refreshDb()
    }

    //TODO: if database is empty

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>>
        get() = _pokemonList

    private val _pokemon = MutableLiveData<PokemonDetails>()
    val pokemon: LiveData<PokemonDetails>
        get() = _pokemon

    private fun setPokemonList() {
        _pokemonList.postValue(db.getFromDb())
    }

    private fun setPokemon(pokemon: PokemonDetails) {
        _pokemon.postValue(pokemon)
    }

    private fun refreshDb() {
        if (isInternetAvailable(context)) {
            val observable = api.getPokemonList()
            val observer = object : Observer<PokemonResponse> {
                override fun onSubscribe(d: Disposable) = Unit

                override fun onNext(t: PokemonResponse) {
                    db.saveToDb(t.pokemons)
                }

                override fun onError(e: Throwable) {
                    println(ERROR_NOT_DOWNLOADED)
                }

                override fun onComplete() {
                    setPokemonList()
                }
            }

            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
        }
    }

    private fun downloadPokemonDetails(number: Int) {
        val observable = api.getPokemon(number)
        val observer = object : Observer<PokemonDetails> {
            override fun onSubscribe(d: Disposable) = Unit

            override fun onNext(t: PokemonDetails) {
                setPokemon(t)
            }

            override fun onError(e: Throwable) {
                println(ERROR_NOT_DOWNLOADED)
            }

            override fun onComplete() = Unit
        }

        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    override fun getPokemon(number: Int): PokemonDetails {
        downloadPokemonDetails(number)
        return pokemon.value!!
    }

    override fun getPokemonsPage(page: Int): List<Pokemon> {
        return pokemonList.value!!.subList(page, page + LIMIT_ON_PAGE)
    }
}