package com.peacecodetech.countries.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.peacecodetech.countries.data.repository.CountryRepository
import com.peacecodetech.countries.model.Info
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountryViewModel(private val repository: CountryRepository) : ViewModel() {

    private val mutableCharacterData: MutableStateFlow<PagingData<Info>?> = MutableStateFlow(null)
    val countryData = mutableCharacterData.asStateFlow()
    init {
        Log.i("ViewModel",":::::::::::::::::::$countryData \n ${getCountries()}")
    }

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