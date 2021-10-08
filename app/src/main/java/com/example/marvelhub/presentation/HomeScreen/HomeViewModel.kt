package com.example.marvelhub.presentation.HomeScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelhub.domain.model.Character
import com.example.marvelhub.domain.useCases.GetCharacterUseCase
import com.example.marvelhub.utils.DataState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getCharacterUseCase: GetCharacterUseCase): ViewModel() {
    private val _characters = MutableLiveData<DataState<List<Character>>>()
    val character :LiveData<DataState<List<Character>>>
        get() = _characters

    fun getCharacters() {
        viewModelScope.launch{
            getCharacterUseCase()

            .collect {
                _characters.postValue(it)
            }
        }

    }
}