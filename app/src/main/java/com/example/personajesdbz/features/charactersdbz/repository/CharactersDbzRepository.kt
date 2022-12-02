package com.example.personajesdbz.features.charactersdbz.repository

import com.example.personajesdbz.db.CharactersDbzDao
import com.example.personajesdbz.db.CharactersDbzEntity
import com.example.personajesdbz.model.CharactersDbzModel

class CharactersDbzRepository(private val charactersDbzDao: CharactersDbzDao) {

    suspend fun getCharactersDbz(
        onSuccess: (ArrayList<CharactersDbzEntity>) -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            onSuccess(
                charactersDbzDao.getAll() as ArrayList<CharactersDbzEntity>
            )
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    suspend fun insertCharactersDbz(
        charactersDbzModel: CharactersDbzModel,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            val characters = CharactersDbzEntity(
                charactersDbzModel.id.toString(),
                charactersDbzModel.name,
                charactersDbzModel.race,
                charactersDbzModel.photo
            )
            charactersDbzDao.insertAll(characters)
            onSuccess()
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    suspend fun deleteCharactersDbz(
        id: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            charactersDbzDao.deleteAll(id)
            onSuccess()
        } catch (e: Exception) {
            onFailure(e)
        }
    }
}
