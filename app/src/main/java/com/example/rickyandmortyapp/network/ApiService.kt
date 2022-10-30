package com.example.rickyandmortyapp.network

import com.example.rickyandmortyapp.model.GetCharactersResponse
import com.example.rickyandmortyapp.util.Constants.API
import com.example.rickyandmortyapp.util.Constants.CHARACTER
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(API.plus(CHARACTER))
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Response<GetCharactersResponse>

    @GET(API.plus(CHARACTER))
    suspend fun searchCharactersForName(
        @Query("name") name: String,
        @Query("page") page: Int,
    ): Response<GetCharactersResponse>

    @GET(API.plus(CHARACTER))
    suspend fun searchCharactersForGender(
        @Query("gender") name: String,
        @Query("page") page: Int,
    ): Response<GetCharactersResponse>

    @GET(API.plus(CHARACTER))
    suspend fun searchCharactersForSpecies(
        @Query("species") name: String,
        @Query("page") page: Int,
    ): Response<GetCharactersResponse>

    @GET(API.plus(CHARACTER))
    suspend fun searchCharactersForStatus(
        @Query("status") name: String,
        @Query("page") page: Int,
    ): Response<GetCharactersResponse>
}