package com.example.pokemons.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {
    @Provides
    @Singleton
    fun provideDb(){

    }

    @Provides
    @Singleton
    fun provideDao(){

    }
}