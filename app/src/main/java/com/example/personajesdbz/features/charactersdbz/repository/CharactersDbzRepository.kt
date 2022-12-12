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
                charactersDbzModel.id,
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
        charactersDbzModel: CharactersDbzModel,
        onSuccess: (CharactersDbzModel) -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            charactersDbzDao.deleteAll(charactersDbzModel.id)
            onSuccess(charactersDbzModel)
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    suspend fun findById(
        idCharacter: String,
        onSuccess: (List<CharactersDbzEntity>) -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            onSuccess(charactersDbzDao.getById(idCharacter) as ArrayList<CharactersDbzEntity>)
        } catch (e: Exception) {
            if (e is NoSuchElementException) {
                onSuccess(ArrayList())
            } else {
                onFailure(e)
            }
        }
    }
}
