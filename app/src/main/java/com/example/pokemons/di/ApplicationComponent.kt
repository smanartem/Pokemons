package com.example.pokemons.di

import com.example.pokemons.MainActivity
import com.example.pokemons.ui.fragmentDetails.FragmentDetails
import com.example.pokemons.ui.fragmentMain.FragmentMain
import dagger.Component


@Component(modules = [
    PokemonModule::class,
    LocalModule::class,
    RepositoryModule::class,
    ApiModule::class])

interface ApplicationComponent {
fun inject(target:MainActivity)

fun inject(target: FragmentMain)

fun inject(target: FragmentDetails)

}