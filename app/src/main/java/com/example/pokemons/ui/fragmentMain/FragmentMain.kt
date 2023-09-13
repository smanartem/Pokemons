package com.example.pokemons.ui.fragmentMain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemons.databinding.FragmentMainBinding
import com.example.pokemons.ui.fragmentNoData.FragmentDialogNoData
import com.example.pokemons.utils.START_PAGE

class FragmentMain : Fragment() {
    private var noDataFragment: FragmentDialogNoData? = null

    private var _binding: FragmentMainBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val adapter = PokemonsListAdapter()
    private val viewModel: FragmentMainVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.livePokemonsList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        refreshUI()
    }

    private fun refreshUI(){
        with(binding){
            pokemonsListRV.layoutManager = LinearLayoutManager(requireContext())
            pokemonsListRV.adapter= adapter
            pokemonsListRV.setHasFixedSize(true)

            pagination.text = START_PAGE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showFragmentNoData(){
        noDataFragment = FragmentDialogNoData()
        noDataFragment?.show(parentFragmentManager, null)
    }
}