package com.example.pokemons.di

import com.example.pokemons.MainActivity
import com.example.pokemons.ui.FragmentDetails.FragmentDetails
import com.example.pokemons.ui.FragmentMain.FragmentMain
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PokemonModule::class, LocalModule::class, RepositoryModule::class])
interface ApplicationComponent {
fun inject(target:MainActivity)

fun inject(target: FragmentMain)
fun inject(target: FragmentDetails)
}