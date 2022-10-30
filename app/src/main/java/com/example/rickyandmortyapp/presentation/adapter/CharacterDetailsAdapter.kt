package com.example.rickyandmortyapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickyandmortyapp.databinding.ItemCharacterDetailBinding
import com.example.rickyandmortyapp.util.Constants.FIELD_SEPARATOR
import com.example.rickyandmortyapp.util.Constants.FILTER_GENDER
import com.example.rickyandmortyapp.util.Constants.FILTER_NAME
import com.example.rickyandmortyapp.util.Constants.FILTER_SPECIES
import com.example.rickyandmortyapp.util.Constants.FILTER_STATUS
import com.example.rickyandmortyapp.util.Constants.ORIGIN
import com.example.rickyandmortyapp.util.Constants.TYPE

class CharacterDetailsAdapter() :
    RecyclerView.Adapter<CharacterDetailsAdapter.RowHolder>() {
    private val characterDetails = mutableListOf<String>()

    inner class RowHolder(private val binding: ItemCharacterDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(characterDetail: String, position: Int) {
            with(binding) {
                tvDetail.text = fieldsNameConverter(position = position).plus(FIELD_SEPARATOR).plus(
                    characterDetail.ifEmpty {
                        "none"
                    }
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding =
            ItemCharacterDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RowHolder(binding)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(characterDetails[position], position)
    }

    override fun getItemCount() = characterDetails.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<String>) {
        characterDetails.clear()
        characterDetails.addAll(list)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearList() {
        characterDetails.clear()
        notifyDataSetChanged()
    }

    fun fieldsNameConverter(position: Int): String {
        when (position) {
            0 -> return FILTER_NAME
            1 -> return FILTER_GENDER
            2 -> return FILTER_STATUS
            3 -> return FILTER_SPECIES
            4 -> return TYPE
            5 -> return ORIGIN
        }
        return ""
    }

}