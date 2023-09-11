package com.example.pokemons.ui

import androidx.lifecycle.ViewModelProvider
import com.example.pokemons.domain.Repository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    repository: Repository
) : ViewModelProvider.Factory{

}