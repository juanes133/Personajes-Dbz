package com.example.personajesdbz.db

import androidx.room.*

@Dao
interface CharactersDbzDao {

    @Query("SELECT * FROM charactersDbz_table")
    suspend fun getAll(): List<CharactersDbzEntity>

    @Query("SELECT * FROM charactersDbz_table WHERE id = :id")
    suspend fun updateAll(id: Int): List<CharactersDbzEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: CharactersDbzEntity)

    @Query("DELETE FROM charactersDbz_table WHERE id = :id")
    suspend fun deleteAll(id: String)

}