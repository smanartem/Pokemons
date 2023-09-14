package com.example.pokemons.di

import android.content.Context
import com.example.pokemons.data.db.LocalDbImpl
import com.example.pokemons.data.db.PokemonDao
import com.example.pokemons.data.db.PokemonsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideDb(): PokemonsDatabase {
        return PokemonsDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideDao(db: PokemonsDatabase): PokemonDao {
        return db.getPokemonDao()
    }

    @Provides
    @Singleton
    fun provideLocalDb(dao: PokemonDao): LocalDbImpl{
        return LocalDbImpl(dao)
    }
}