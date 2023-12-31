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
import com.example.pokemons.utils.isInternetAvailable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: ApiInterfaceImpl,
    private val db: LocalDbImpl,
    private val context: Context
) {

    init {
        refreshDb()
    }

    val mustShowNoData = MutableLiveData<Boolean>()

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>>
        get() = _pokemonList

    private val _pokemon = MutableLiveData<PokemonDetails>()
    val pokemon: LiveData<PokemonDetails>
        get() = _pokemon

    private fun refreshDb() {
        if (isInternetAvailable(context)) {
            api.getPokemonList()
                .subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<PokemonResponse> {
                    override fun onSubscribe(d: Disposable) = Unit

                    override fun onNext(t: PokemonResponse) {
                        db.saveToDb(t.results)
                    }

                    override fun onError(e: Throwable) {
                        println(ERROR_NOT_DOWNLOADED)
                        println("Message : " + e.message)
                    }

                    override fun onComplete() = Unit
                })
        }
        getDataFromLocal()
    }

    private fun getDataFromLocal() {
        db.getFromDb()
            .observeOn(Schedulers.io())
            .subscribe(object : Observer<List<Pokemon>> {
                override fun onSubscribe(d: Disposable) = Unit

                override fun onNext(t: List<Pokemon>) {
                    _pokemonList.postValue(t)
                    mustShowNoData.postValue(false)
                }

                override fun onError(e: Throwable) {
                    _pokemonList.postValue(emptyList())
                    mustShowNoData.postValue(true)
                }

                override fun onComplete() = Unit
            })

    }

    private fun downloadPokemonDetails(path: String) {
        api.getPokemon(path)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<PokemonDetails> {
                override fun onSubscribe(d: Disposable) = Unit

                override fun onNext(t: PokemonDetails) {
                    _pokemon.postValue(t)
                    mustShowNoData.postValue(false)
                }

                override fun onError(e: Throwable) = mustShowNoData.postValue(true)

                override fun onComplete() = Unit
            })
    }

    fun loadPokemon(path: String) {
        downloadPokemonDetails(path)
    }
}