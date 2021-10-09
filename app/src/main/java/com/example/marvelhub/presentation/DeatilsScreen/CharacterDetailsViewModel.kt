package com.example.marvelhub.presentation.DeatilsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.marvelhub.domain.model.Character
import com.example.marvelhub.domain.useCases.GetCharacterByIdUseCase
import com.example.marvelhub.domain.useCases.GetCharacterComics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor
    (private val getCharacterByIdUseCase: GetCharacterByIdUseCase,private val getCharacterComics: GetCharacterComics
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

}