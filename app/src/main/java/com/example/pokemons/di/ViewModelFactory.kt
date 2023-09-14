package com.example.pokemons.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemons.domain.repository.Repository
import com.example.pokemons.ui.fragmentDetails.FragmentDetails
import com.example.pokemons.ui.fragmentDetails.FragmentDetailsVM
import com.example.pokemons.ui.fragmentMain.FragmentMain
import com.example.pokemons.ui.fragmentMain.FragmentMainVM
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            FragmentMain::class.java -> FragmentMainVM(repository)
            FragmentDetails::class.java -> FragmentDetailsVM(repository)
            else -> IllegalStateException("unknown class")
        } as T
    }
}