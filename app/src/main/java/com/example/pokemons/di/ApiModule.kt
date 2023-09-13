package com.example.pokemons.di

import com.example.pokemons.data.api.PokemonApiImpl
import com.example.pokemons.data.api.Retrofit
import com.example.pokemons.data.api.PokemonApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): PokemonApi {
        return PokemonApiImpl(retrofit)
    }
}