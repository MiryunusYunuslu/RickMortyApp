package com.example.rickyandmortyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickyandmortyapp.FilterSelection
import com.example.rickyandmortyapp.model.GetCharactersResponse
import com.example.rickyandmortyapp.repository.RemoteRepository
import com.example.rickyandmortyapp.util.Constants.FILTER_GENDER
import com.example.rickyandmortyapp.util.Constants.FILTER_NAME
import com.example.rickyandmortyapp.util.Constants.FILTER_SPECIES
import com.example.rickyandmortyapp.util.Constants.FILTER_STATUS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RemoteRepository) : ViewModel() {
    private var _getCharactersLiveData: MutableLiveData<GetCharactersResponse>? = null
    val getCharactersLiveData get() = _getCharactersLiveData!!

    private var searchedCharactersResponse: Response<GetCharactersResponse>? = null

    fun getCharacters(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCharacters(page)
            getCharactersLiveData.postValue(response.body())
        }
    }

    fun searchCharacters(page: Int, value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (FilterSelection.selectedFilterName) {
                FILTER_NAME -> {
                    searchedCharactersResponse =
                        repository.searchCharactersForName(page, value)
                }

                FILTER_GENDER -> {
                    searchedCharactersResponse = repository.searchCharactersForGender(
                        page,
                        value
                    )
                }

                FILTER_SPECIES -> {
                    searchedCharactersResponse = repository.searchCharactersForSpecies(
                        page,
                        value
                    )
                }

                FILTER_STATUS -> {
                    searchedCharactersResponse = repository.searchCharactersForStatus(
                        page,
                        value
                    )
                }
            }
            searchedCharactersResponse?.let { response ->
                if (response.isSuccessful) {
                    getCharactersLiveData.postValue(response.body())
                }

            }

        }
    }

    init {
        _getCharactersLiveData = MutableLiveData()
    }
}