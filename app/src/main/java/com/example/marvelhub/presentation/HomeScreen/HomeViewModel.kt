package com.example.marvelhub.presentation.HomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.marvelhub.domain.useCases.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val getAllCharactersUseCase: GetAllCharactersUseCase): ViewModel() {
   suspend fun  getCharacters() = getAllCharactersUseCase().cachedIn(viewModelScope)
}