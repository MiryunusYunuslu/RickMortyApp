package com.example.rickyandmortyapp.presentation

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickyandmortyapp.R
import com.example.rickyandmortyapp.databinding.FragmentHomeBinding
import com.example.rickyandmortyapp.model.CharacterResult
import com.example.rickyandmortyapp.presentation.adapter.HomeAdapter
import com.example.rickyandmortyapp.util.Constants.BOTTOM_SHEET_TAG
import com.example.rickyandmortyapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), CharacterItemClicked {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var _viewModel: MainViewModel? = null
    private val viewModel get() = _viewModel!!

    private var page = 1
    private var limit = 2
    private var charactersList = ArrayList<CharacterResult>()
    private var isFiltered = false
    private var filterText = ""

    private val homeAdapter by lazy {
        HomeAdapter(charactersList, this)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initToolbar()
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setFabAction()
        initRecyclerView()
        initRefreshLayout()
        setPagination()
        setObservers()
        getRemoteData(true)

    }

    private fun setFabAction() {
        binding.fabMain.setOnClickListener {
            FilterBottomSheetDialogFragment().show(parentFragmentManager, BOTTOM_SHEET_TAG)
        }
    }

    private fun initRefreshLayout() = with(binding) {
        swipeMain.setOnRefreshListener {
            pbMain.isVisible = true
            swipeMain.isRefreshing = false
            charactersList.clear()
            homeAdapter.clearList()
            resetPage()
            getRemoteData(true)
        }
    }

    private fun resetPage() {
        page = 1
        limit = 2
        isFiltered = false
    }


    private fun setObservers() {
        viewModel.getCharactersLiveData.observe(viewLifecycleOwner, Observer { characterResult ->
            binding.pbMain.isVisible = false
            val oldCount = charactersList.size
            charactersList.addAll(characterResult.results)
            limit = characterResult.info.pages
            homeAdapter.notifyItemRangeInserted(oldCount, charactersList.size)

        })
    }

    private fun setPagination() = with(binding) {
        rvMain.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!rvMain.canScrollVertically(1)) {
                    page++
                    if (page < limit) {
                        if (isFiltered) {
                            getRemoteDataFiltered(filterText, false)
                        } else {
                            getRemoteData(false)
                        }

                    }

                }
            }
        })
    }

    private fun getRemoteData(showLoading: Boolean) = with(binding) {
        pbMain.isVisible = showLoading
        viewModel.getCharacters(page)
    }

    private fun getRemoteDataFiltered(value: String, showLoading: Boolean) = with(binding) {
        binding.pbMain.isVisible = showLoading
        viewModel.searchCharacters(page, value)
    }

    private fun initRecyclerView() = with(binding) {
        rvMain.layoutManager = GridLayoutManager(requireContext(), 2)
        rvMain.adapter = homeAdapter
    }

    private fun initToolbar() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.action_search -> {
                        val searchView = menuItem.actionView as SearchView
                        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                            override fun onQueryTextSubmit(p0: String?): Boolean {
                                isFiltered = true

                                charactersList.clear()
                                homeAdapter.clearList()

                                p0?.let {
                                    filterText = it
                                    getRemoteDataFiltered(filterText, true)
                                }

                                return false
                            }

                            override fun onQueryTextChange(p0: String?): Boolean {
                                return false
                            }

                        })
                    }
                }
                return false
            }
        })
    }

    override fun onDestroy() {
        _viewModel = null
        _binding = null
        super.onDestroy()
    }

    override fun onCharacterClicked(character: CharacterResult) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(character)
        findNavController().navigate(action)
    }

}