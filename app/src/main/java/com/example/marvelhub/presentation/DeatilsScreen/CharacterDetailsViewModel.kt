package com.example.marvelhub.presentation.DeatilsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.marvelhub.domain.model.Character
import com.example.marvelhub.domain.useCases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor
    (private val getCharacterByIdUseCase: GetCharacterByIdUseCase,private val getCharacterComics: GetCharacterComics
    ,private val getCharacterEvents: GetCharacterEvents,private val getCharacterSeries: GetCharacterSeries,private val getCharacterStories: GetCharacterStories
): ViewModel() {
    private val _character = MutableLiveData<Character>()
    val character :LiveData<Character>
    get() = _character

    fun getCharacterData(id:Int){
        viewModelScope.launch (Dispatchers.IO){
               _character.postValue(getCharacterByIdUseCase(id)!!)
        }
    }
    suspend fun getCharacterComicsByID(id:Int) = getCharacterComics(id).cachedIn(viewModelScope)
    suspend fun getCharacterEventsByID(id:Int) = getCharacterEvents(id).cachedIn(viewModelScope)
    suspend fun getCharacterSeriesByID(id:Int) = getCharacterSeries(id).cachedIn(viewModelScope)
    suspend fun getCharacterStoriesByID(id:Int) = getCharacterStories(id).cachedIn(viewModelScope)

}