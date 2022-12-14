package com.example.personajesdbz.features.charactersdbz.viewmodel

import androidx.lifecycle.*
import com.example.personajesdbz.Event
import com.example.personajesdbz.features.charactersdbz.repository.CharactersDbzRepository
import com.example.personajesdbz.model.CharactersDbzModel
import kotlinx.coroutines.launch

class CharactersDbzViewModel(private val charactersDbzRepository: CharactersDbzRepository) :
    ViewModel() {

    private val mutableCharactersDbzList = MutableLiveData<Event<ArrayList<CharactersDbzModel>>>()
    val charactersDbzList: LiveData<Event<ArrayList<CharactersDbzModel>>> get() = mutableCharactersDbzList

    private val mutableCharactersDbzDeleted = MutableLiveData<Event<CharactersDbzModel>>()
    val charactersDbzDeleted: LiveData<Event<CharactersDbzModel>> get() = mutableCharactersDbzDeleted

    private val mutableCharactersDbzError = MutableLiveData<Event<Exception>>()
    val charactersDbzError: LiveData<Event<Exception>> get() = mutableCharactersDbzError

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
                mutableCharactersDbzList.value = Event(result)
            }, {
                mutableCharactersDbzError.value = Event(it)
            })
        }
    }

    fun deleteCharactersDbz(charactersDbzModel: CharactersDbzModel) {
        viewModelScope.launch {
            charactersDbzRepository.deleteCharactersDbz(charactersDbzModel, {
                mutableCharactersDbzDeleted.value = Event(it)
            }) {
                mutableCharactersDbzError.value = Event(it)
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


