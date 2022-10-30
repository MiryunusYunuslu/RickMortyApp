package com.example.rickyandmortyapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickyandmortyapp.helper.FilterSelection
import com.example.rickyandmortyapp.R
import com.example.rickyandmortyapp.databinding.FragmentFilterBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterBottomSheetDialogFragment() :
    BottomSheetDialogFragment() {
    private lateinit var binding: FragmentFilterBottomSheetDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBottomSheetDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFilterActions()
        initClickEvents()
    }

    private fun initClickEvents() = with(binding) {
        btnDone.setOnClickListener {
            dismiss()
        }
    }

    private fun initFilterActions() = with(binding) {
        listOf(
            tvFilterName,
            tvFilterGender,
            tvFilterSpecies,
            tvFilterStatus
        ).forEachIndexed { position, tvFilter ->
            tvFilter.setOnClickListener {
                FilterSelection.selectedFilterCode = position
                FilterSelection.selectedFilterName = tvFilter.text.toString()
                unCheckFilters(position)
            }

            tvFilter.isActivated = FilterSelection.selectedFilterCode == position
        }
    }

    private fun unCheckFilters(position: Int) = with(binding) {
        listOf(
            tvFilterName,
            tvFilterGender,
            tvFilterSpecies,
            tvFilterStatus
        ).forEachIndexed { tvPosition, tvFilter ->
            tvFilter.isActivated = tvPosition == position
        }
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onDestroy() {
        super.onDestroy()
        dismiss()
    }


}