package com.example.rickyandmortyapp.repository

import com.example.rickyandmortyapp.network.ApiService
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getCharacters(page: Int) = apiService.getCharacters(page)

    suspend fun searchCharactersForName(page: Int, name: String) =
        apiService.searchCharactersForName(name, page)

    suspend fun searchCharactersForSpecies(page: Int, species: String) =
        apiService.searchCharactersForSpecies(species, page)

    suspend fun searchCharactersForGender(page: Int, gender: String) =
        apiService.searchCharactersForGender(gender, page)

    suspend fun searchCharactersForStatus(page: Int, status: String) =
        apiService.searchCharactersForStatus(status, page)
}