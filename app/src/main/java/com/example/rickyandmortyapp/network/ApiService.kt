package com.example.rickyandmortyapp.network

import com.example.rickyandmortyapp.model.GetCharactersResponse
import com.example.rickyandmortyapp.util.Constants.API
import com.example.rickyandmortyapp.util.Constants.CHARACTER
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {
    @GET(API.plus(CHARACTER))
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Response<GetCharactersResponse>

    @GET(API.plus(CHARACTER))
    suspend fun searchCharacters(
        @Query("page") page: Int,
        @QueryMap map: Map<String, String>
    ): Response<GetCharactersResponse>
}