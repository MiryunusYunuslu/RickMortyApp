package com.example.rickyandmortyapp.model

import java.io.Serializable

data class CharacterResult(
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val type: String,
    val gender: String,
    val origin: Origin
) : Serializable

data class Origin(val name: String) : Serializable