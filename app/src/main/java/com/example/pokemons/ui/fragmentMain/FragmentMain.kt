package com.example.pokemons.ui.fragmentMain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemons.MainActivity
import com.example.pokemons.R
import com.example.pokemons.databinding.FragmentMainBinding
import com.example.pokemons.di.ViewModelFactory
import com.example.pokemons.ui.fragmentNoData.FragmentDialogNoData
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
            bundleOf(it to String)
        )
    }

    private val viewModel by activityViewModels<FragmentMainVM> { viewModelFactory}

    init {
        println("Fragment Main was created")
    }

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

        viewModel.livePokemonsList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
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

    fun showFragmentNoData() {
        noDataFragment = FragmentDialogNoData()
        noDataFragment?.show(parentFragmentManager, null)
    }
}