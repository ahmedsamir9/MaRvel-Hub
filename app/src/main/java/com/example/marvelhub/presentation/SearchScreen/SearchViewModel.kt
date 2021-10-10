package com.example.marvelhub.presentation.SearchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.marvelhub.domain.useCases.GetCharactersByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getCharactersByNameUseCase: GetCharactersByNameUseCase): ViewModel() {
    suspend fun getCharactersByName(query :String)= getCharactersByNameUseCase(query).cachedIn(viewModelScope)
}