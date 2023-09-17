package com.example.pokemons.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemons.domain.repository.Repository
import com.example.pokemons.ui.fragmentMain.FragmentMainVM
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FragmentMainVM(repository) as T
    }
}