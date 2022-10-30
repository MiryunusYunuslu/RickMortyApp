package com.example.rickyandmortyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickyandmortyapp.FilterSelection
import com.example.rickyandmortyapp.model.GetCharactersResponse
import com.example.rickyandmortyapp.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RemoteRepository) : ViewModel() {
    private var _getCharactersLiveData: MutableLiveData<GetCharactersResponse>? = null
    val getCharactersLiveData get() = _getCharactersLiveData!!

    fun getCharacters(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCharacters(page)
            getCharactersLiveData.postValue(response.body())
        }
    }

    fun searchCharacters(page: Int, value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val queryMap = hashMapOf<String, String>()
            queryMap[FilterSelection.selectedFilterName] = value
            val response = repository.searchCharacters(page, queryMap)
            if (response.isSuccessful) {
                getCharactersLiveData.postValue(response.body())
            }

        }
    }

    init {
        _getCharactersLiveData = MutableLiveData()
    }
}