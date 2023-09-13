package com.example.pokemons.ui.fragmentNoData

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemons.databinding.FragmentNoDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FragmentDialogNoData : BottomSheetDialogFragment() {

    private var binding: FragmentNoDataBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoDataBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}