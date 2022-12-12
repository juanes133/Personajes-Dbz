package com.example.personajesdbz.db

import androidx.room.*

@Dao
interface CharactersDbzDao {

    @Query("SELECT * FROM charactersDbz_table")
    suspend fun getAll(): List<CharactersDbzEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: CharactersDbzEntity)

    @Query("DELETE FROM charactersDbz_table WHERE id = :id")
    suspend fun deleteAll(id: String)

    @Query("SELECT * FROM charactersDbz_table WHERE id = :id")
    suspend fun getById(id: String): List<CharactersDbzEntity>

}