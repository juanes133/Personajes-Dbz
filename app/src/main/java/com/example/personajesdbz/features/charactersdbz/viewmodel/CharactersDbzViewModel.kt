package com.example.personajesdbz.features.charactersdbz.viewmodel

import androidx.lifecycle.*
import com.example.personajesdbz.features.charactersdbz.repository.CharactersDbzRepository
import com.example.personajesdbz.model.CharactersDbzModel
import kotlinx.coroutines.launch

class CharactersDbzViewModel(private val charactersDbzRepository: CharactersDbzRepository) :
    ViewModel() {

    private val mutableCharactersDbzList = MutableLiveData<ArrayList<CharactersDbzModel>>()
    val charactersDbzList: LiveData<ArrayList<CharactersDbzModel>> get() = mutableCharactersDbzList

    private val mutableCharactersDbzInsert = MutableLiveData<Boolean>()
    val charactersDbzInsert: LiveData<Boolean> get() = mutableCharactersDbzInsert

    private val mutableCharactersDbzDelete = MutableLiveData<Boolean>()
    val charactersDbzDelete: LiveData<Boolean> get() = mutableCharactersDbzDelete

    private val mutableCharactersDbzError = MutableLiveData<Exception>()
    val charactersDbzError: LiveData<Exception> get() = mutableCharactersDbzError

    fun getCharactersDbz() {
        viewModelScope.launch {
            charactersDbzRepository.getCharactersDbz({ list ->
                val result = ArrayList<CharactersDbzModel>()
                list.forEach {
                    result.add(
                        CharactersDbzModel(
                            it.id,
                            it.name,
                            it.race,
                            it.photo
                        )
                    )
                }
                mutableCharactersDbzList.value = result
            }, {
                mutableCharactersDbzError.value = it
            })
        }
    }

    fun insertCharactersDbz(charactersDbzModel: CharactersDbzModel) {
        viewModelScope.launch {
            charactersDbzRepository.insertCharactersDbz(charactersDbzModel, {
                mutableCharactersDbzInsert.value = true
            }, {
                mutableCharactersDbzError.value = it
            })
        }
    }

    fun deleteCharactersDbz(id: String) {
        viewModelScope.launch {
            charactersDbzRepository.deleteCharactersDbz(id, {
                mutableCharactersDbzDelete.value = true
            }) {
                mutableCharactersDbzError.value = it
            }
        }
    }
}

class CharactersDbzViewModelFactory(
    private val charactersDbzRepository: CharactersDbzRepository,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharactersDbzViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharactersDbzViewModel(charactersDbzRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


