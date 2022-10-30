package com.example.rickyandmortyapp.presentation

import com.example.rickyandmortyapp.model.CharacterResult

interface CharacterItemClicked {
    fun onCharacterClicked(character: CharacterResult)
}