package com.example.personajesdbz.features.entercharacters.viewmodel

import androidx.lifecycle.*
import com.example.personajesdbz.Event
import com.example.personajesdbz.features.charactersdbz.repository.CharactersDbzRepository
import com.example.personajesdbz.model.CharactersDbzModel
import kotlinx.coroutines.launch

class EnterCharactersViewModel(private val charactersDbzRepository: CharactersDbzRepository) :
    ViewModel() {

    private val mutableCharactersDbzList = MutableLiveData<Event<ArrayList<CharactersDbzModel>>>()
    val charactersDbzList: LiveData<Event<ArrayList<CharactersDbzModel>>> get() = mutableCharactersDbzList

    private val mutableCharactersDbzInsert = MutableLiveData<Event<Boolean>>()
    val charactersDbzInsert: LiveData<Event<Boolean>> get() = mutableCharactersDbzInsert

    private val mutableCharactersDbzError = MutableLiveData<Event<Exception>>()
    val charactersDbzError: LiveData<Event<Exception>> get() = mutableCharactersDbzError

    fun insertCharactersDbz(charactersDbzModel: CharactersDbzModel) {
        viewModelScope.launch {
            charactersDbzRepository.insertCharactersDbz(charactersDbzModel, {
                mutableCharactersDbzInsert.value = Event(true)
            }, {
                mutableCharactersDbzError.value = Event(it)
            })
        }
    }

    fun findById(id: String) {
        viewModelScope.launch {
            charactersDbzRepository.findById(id, { list ->
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
            }) {
                mutableCharactersDbzError.value = Event(it)
            }
        }
    }

}

class EnterCharactersViewModelFactory(
    private val charactersDbzRepository: CharactersDbzRepository,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EnterCharactersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EnterCharactersViewModel(charactersDbzRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}