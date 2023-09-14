package com.example.pokemons.di

import com.example.pokemons.data.api.PokemonApi
import com.example.pokemons.data.api.PokemonApiImpl
import com.example.pokemons.data.api.Retrofit
import dagger.Module
import dagger.Provides
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): PokemonApi {
        return PokemonApiImpl(retrofit)
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(): retrofit2.Retrofit {
        return retrofit2.Retrofit
            .Builder()
            .baseUrl("https://example.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}