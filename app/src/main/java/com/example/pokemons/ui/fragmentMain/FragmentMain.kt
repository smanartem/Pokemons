package com.example.pokemons.ui.fragmentMain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemons.MainActivity
import com.example.pokemons.R
import com.example.pokemons.data.models.Pokemon
import com.example.pokemons.databinding.FragmentMainBinding
import com.example.pokemons.di.ViewModelFactory
import com.example.pokemons.ui.fragmentNoData.FragmentDialogNoData
import com.example.pokemons.utils.KEY_ARGUMENT
import com.example.pokemons.utils.LIMIT_ON_PAGE
import com.example.pokemons.utils.START_PAGE
import javax.inject.Inject

class FragmentMain : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var noDataFragment: FragmentDialogNoData? = null

    private var _binding: FragmentMainBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val adapter = PokemonsListAdapter {
        findNavController().navigate(
            R.id.action_fragmentMain_to_fragmentDetails,
            bundleOf(KEY_ARGUMENT to it)
        )
    }

    private val viewModel by activityViewModels<FragmentMainVM> { viewModelFactory }

    private var currentPage = START_PAGE
    private var wholeList: List<Pokemon> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).component.inject(this)

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pokemonListLive.observe(viewLifecycleOwner) {
            wholeList = it
            val filteredList = filterItStartPage(currentPage, wholeList)
            adapter.submitList(filteredList)
        }

        refreshUI()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.nextBtn.setOnClickListener {
            currentPage++
            adapter.submitList(filterItAfterClick(currentPage, wholeList))
            binding.pagination.text = currentPage.toString()
        }

        binding.previousBtn.setOnClickListener {
            if (currentPage != START_PAGE) {
                currentPage--
                adapter.submitList(filterItAfterClick(currentPage, wholeList))
                binding.pagination.text = currentPage.toString()
            }
        }
    }

    private fun refreshUI() {
        with(binding) {
            pokemonsListRV.layoutManager = LinearLayoutManager(requireContext())
            pokemonsListRV.adapter = adapter
            pokemonsListRV.setHasFixedSize(true)

            pagination.text = currentPage.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun filterItStartPage(page: Int, list: List<Pokemon>): List<Pokemon> {
        if (list.isNotEmpty()) {
            return list.subList(page, page + LIMIT_ON_PAGE)
        } else {
            return emptyList()
        }
    }

    private fun filterItAfterClick(page: Int, list: List<Pokemon>): List<Pokemon> {
        val from = START_PAGE + (LIMIT_ON_PAGE * (page - 1))
        val to = LIMIT_ON_PAGE * page
        return list.subList(from, to)
    }

    fun showFragmentNoData() {
        noDataFragment = FragmentDialogNoData()
        noDataFragment?.show(parentFragmentManager, null)
    }
}