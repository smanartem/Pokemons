package com.example.pokemons.di

import android.content.Context
import com.example.pokemons.data.api.ApiInterfaceImpl
import com.example.pokemons.data.db.LocalDbImpl
import com.example.pokemons.domain.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(api: ApiInterfaceImpl, local: LocalDbImpl, context: Context): Repository {
        return Repository(api, local, context)
    }
}