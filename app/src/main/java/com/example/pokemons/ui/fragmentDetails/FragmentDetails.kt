package com.example.pokemons.ui.fragmentDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pokemons.MainActivity
import com.example.pokemons.data.models.PokemonDetails
import com.example.pokemons.databinding.FragmentDetailsBinding
import com.example.pokemons.di.ViewModelFactory
import com.example.pokemons.utils.KEY_ARGUMENT
import javax.inject.Inject

class FragmentDetails : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val viewModel by activityViewModels<FragmentDetailsVM> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).component.inject(this)
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonNumber = arguments?.getInt(KEY_ARGUMENT)
        viewModel.downloadPokemon(pokemonNumber!!)

        viewModel.livePokemon.observe(viewLifecycleOwner) {
            refreshUI(it)
        }
    }

    private fun refreshUI(pokemon: PokemonDetails) {
        with(binding) {
            type.text = pokemon.types[0].type.toString()
            weight.text = pokemon.weight.toString()
            height.text = pokemon.height.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}