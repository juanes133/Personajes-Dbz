package com.example.personajesdbz

import android.app.Application
import com.example.personajesdbz.db.CharactersDbzDataBase
import com.example.personajesdbz.features.charactersdbz.repository.CharactersDbzRepository

class CharactersDbzApplication : Application() {

    private val roomDataBase by lazy { CharactersDbzDataBase.getDatabase(this) }
    val charactersDbzRepository by lazy { CharactersDbzRepository(roomDataBase.charactersDbzDao()) }
}