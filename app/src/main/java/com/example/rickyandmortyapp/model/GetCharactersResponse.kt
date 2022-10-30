package com.example.rickyandmortyapp.model

import java.io.Serializable

data class GetCharactersResponse(val info: Info, val results: List<CharacterResult>) : Serializable

data class Info(
    val pages: Int,
    val next: String?
)

