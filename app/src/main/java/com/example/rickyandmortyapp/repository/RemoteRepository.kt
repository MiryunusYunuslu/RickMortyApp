package com.example.rickyandmortyapp.repository

import com.example.rickyandmortyapp.network.ApiService
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getCharacters(page: Int) = apiService.getCharacters(page)

    suspend fun searchCharacters(page: Int, queryMap: HashMap<String, String>) =
        apiService.searchCharacters(page, queryMap)
}