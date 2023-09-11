package com.example.pokemons.di

import com.example.pokemons.data.repository.PokemonApiImpl
import com.example.pokemons.data.retrofit.Api
import com.example.pokemons.domain.PokemonApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideApi(api: Api): PokemonApi{
        return PokemonApiImpl(api)
    }
}