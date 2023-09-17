package com.example.pokemons.ui.fragmentDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokemons.MainActivity
import com.example.pokemons.data.models.PokemonDetails
import com.example.pokemons.databinding.FragmentDetailsBinding
import com.example.pokemons.di.ViewModelFactory
import com.example.pokemons.utils.KEY_ARGUMENT
import javax.inject.Inject

class FragmentDetails : Fragment() {
    private lateinit var viewModel: FragmentDetailsVM

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = requireNotNull(_binding)

    // private val viewModel by activityViewModels<FragmentDetailsVM> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[FragmentDetailsVM::class.java]

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonPath = arguments?.getString(KEY_ARGUMENT)
        viewModel.setPokemonNumber(pokemonPath!!)

        viewModel.pokemonLive.observe(viewLifecycleOwner) {
            refreshUI(it)
        }
    }

    private fun refreshUI(pokemon: PokemonDetails) {
        with(binding) {
            Glide.with(requireContext())
                .load(pokemon.sprites.other.home.front_default)
                .into(avatar)

            type.text = buildString {
                append("Type:   ")
                append(pokemon.types[0].type.name)
            }
            weight.text = buildString {
                append("Wieght:   ")
                append(pokemon.weight)
            }
            height.text = buildString {
                append("Height:   ")
                append(pokemon.height)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}