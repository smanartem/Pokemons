package com.example.pokemons.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokemons.utils.TABLE_NAME

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
abstract class PokemonsDatabase : RoomDatabase() {
    abstract fun getPokemonDao(): PokemonDao

    companion object {
        @Volatile
        private var INSTANCE: PokemonsDatabase? = null

        fun getDatabase(context: Context): PokemonsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        PokemonsDatabase::class.java,
                        TABLE_NAME
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}