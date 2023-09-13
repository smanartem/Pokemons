package com.example.pokemons

import android.app.Application

class App: Application() {
    //TODO: add modules dagger here
    val appComponent = DaggerApplicationComponent
        .builder()
        .build()
}