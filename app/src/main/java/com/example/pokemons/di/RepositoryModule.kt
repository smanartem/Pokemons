package com.example.pokemons.di

import com.example.pokemons.domain.repository.RepositoryImpl
import com.example.pokemons.data.db.LocalDb
import com.example.pokemons.data.api.PokemonApi
import com.example.pokemons.domain.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(api: PokemonApi, local: LocalDb): Repository {
        return RepositoryImpl(api, local)
    }
}