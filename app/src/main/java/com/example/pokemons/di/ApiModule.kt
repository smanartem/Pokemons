package com.example.pokemons.di

import com.example.pokemons.data.api.PokemonApiImpl
import com.example.pokemons.data.api.retrofit
import com.example.pokemons.data.api.PokemonApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideApi(retrofit: retrofit): PokemonApi {
        return PokemonApiImpl(retrofit)
    }
}