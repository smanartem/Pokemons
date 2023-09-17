package com.example.pokemons.ui.fragmentMain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
    private lateinit var viewModel: FragmentMainVM

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

    //  private val viewModel by activityViewModels<FragmentMainVM> { viewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).component.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[FragmentMainVM::class.java]

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pokemonListLive.observe(viewLifecycleOwner) {
            println("*** FragmentMain -> ${it.size} items to submit")
            val filteredList = filterIt(START_PAGE, it)
            println("*** FragmentMain -> filtered list = ${filteredList.size}")
            adapter.submitList(filteredList)
        }

        refreshUI()
    }

    private fun refreshUI() {
        with(binding) {
            pokemonsListRV.layoutManager = LinearLayoutManager(requireContext())
            pokemonsListRV.adapter = adapter
            pokemonsListRV.setHasFixedSize(true)

            pagination.text = START_PAGE.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun filterIt(page: Int, list: List<Pokemon>): List<Pokemon> {
        if(list.isNotEmpty()){
            return list.subList(page, page + LIMIT_ON_PAGE)
        }else{
            return emptyList()
        }

    }

    fun showFragmentNoData() {
        noDataFragment = FragmentDialogNoData()
        noDataFragment?.show(parentFragmentManager, null)
    }
}