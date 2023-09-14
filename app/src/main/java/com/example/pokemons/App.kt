package com.example.pokemons

import android.app.Application
import com.example.pokemons.di.DaggerApplicationComponent
import com.example.pokemons.di.PokemonModule

class App : Application() {

    val appComponent = DaggerApplicationComponent
        .builder()
        .pokemonModule(PokemonModule(this))
        .build()
}