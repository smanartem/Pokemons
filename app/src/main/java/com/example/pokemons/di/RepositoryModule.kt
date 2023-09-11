package com.example.pokemons.di

import com.example.pokemons.data.repository.RepositoryImpl
import com.example.pokemons.domain.LocalDb
import com.example.pokemons.domain.PokemonApi
import com.example.pokemons.domain.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(api: PokemonApi, local: LocalDb): Repository{
        return RepositoryImpl(api, local)
    }
}