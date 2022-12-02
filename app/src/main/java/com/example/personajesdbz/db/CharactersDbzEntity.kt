package com.example.personajesdbz.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "charactersDbz_table")
data class CharactersDbzEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val race: String,
    val photo: String,
)
