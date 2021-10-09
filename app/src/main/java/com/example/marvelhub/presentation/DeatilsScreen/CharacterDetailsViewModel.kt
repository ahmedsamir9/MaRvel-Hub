package com.example.marvelhub.presentation.DeatilsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelhub.domain.model.Character
import com.example.marvelhub.domain.useCases.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(private val getCharacterByIdUseCase: GetCharacterByIdUseCase): ViewModel() {
    private val _character = MutableLiveData<Character>()
    val character :LiveData<Character>
    get() = _character

    fun getCharacterData(id:Int){
        viewModelScope.launch (Dispatchers.IO){
               _character.postValue(getCharacterByIdUseCase(id)!!)
        }
    }
}