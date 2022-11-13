package com.peacecodetech.countries.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.peacecodetech.countries.data.repository.CountryRepository
import com.peacecodetech.countries.model.Countries
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountryViewModel(private val repository: CountryRepository) : ViewModel() {

    private val mutableCharacterData: MutableStateFlow<PagingData<Countries>?> =
        MutableStateFlow(null)
    val characterData = mutableCharacterData.asStateFlow()

    fun getCountries() {
        viewModelScope.launch {
            repository.getCountries()
                .cachedIn(viewModelScope)
                .collect { data ->
                    mutableCharacterData.value = data
                }
        }
    }
}