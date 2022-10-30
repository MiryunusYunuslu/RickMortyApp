package com.example.rickyandmortyapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickyandmortyapp.databinding.FragmentDetailsBinding
import com.example.rickyandmortyapp.presentation.adapter.CharacterDetailsAdapter
import com.example.rickyandmortyapp.util.setImageGlide


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs<DetailsFragmentArgs>()

    private val characterDetailsAdapter by lazy {
        CharacterDetailsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initClickEvent()
        setData()
    }

    private fun initClickEvent() = with(binding) {
        ibReturn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

    private fun setData() {
        val data = args.detail

        binding.ivCharacter.setImageGlide(data.image)
        val characterDetails =
            listOf(
                data.name,
                data.gender,
                data.status,
                data.species,
                data.type,
                data.origin.name
            )

        characterDetailsAdapter.submitList(characterDetails)
    }

    private fun initRecyclerView() = with(binding.rvCharacters) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = characterDetailsAdapter
    }

}