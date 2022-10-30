package com.example.rickyandmortyapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickyandmortyapp.databinding.ItemCharactersBinding
import com.example.rickyandmortyapp.model.CharacterResult
import com.example.rickyandmortyapp.presentation.CharacterItemClicked
import com.example.rickyandmortyapp.util.setImageGlide

class HomeAdapter(
    private val mortyCharacters: ArrayList<CharacterResult>,
    val view: CharacterItemClicked
) :
    RecyclerView.Adapter<HomeAdapter.RowHolder>() {

    inner class RowHolder(private val binding: ItemCharactersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(characterResult: CharacterResult) {
            with(binding) {
                tvGender.text = characterResult.gender
                tvName.text = characterResult.name
                tvSpecies.text = characterResult.species
                tvStatus.text = characterResult.status
                ivCharacter.setImageGlide(characterResult.image)
                root.setOnClickListener {
                    view.onCharacterClicked(characterResult)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding =
            ItemCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RowHolder(binding)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(mortyCharacters[position])
    }

    override fun getItemCount() = mortyCharacters.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<CharacterResult>) {
        mortyCharacters.clear()
        mortyCharacters.addAll(list)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearList() {
        mortyCharacters.clear()
        notifyDataSetChanged()
    }

}