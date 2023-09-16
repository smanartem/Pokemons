package com.example.pokemons.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.pokemons.domain.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PokemonModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideViewModelFactory(
        repository: Repository
    ): ViewModelProvider.Factory = ViewModelFactory(
        repository = repository
    )
}