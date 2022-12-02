package com.example.personajesdbz.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CharactersDbzEntity::class], version = 1)

abstract class CharactersDbzDataBase : RoomDatabase() {
    abstract fun charactersDbzDao(): CharactersDbzDao

    companion object {
        @Volatile
        private var INSTANCE: CharactersDbzDataBase? = null

        fun getDatabase(context: Context): CharactersDbzDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharactersDbzDataBase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}