package com.example.pokemons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.pokemons.databinding.ActivityMainBinding
import com.example.pokemons.di.ApplicationComponent

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val navController: NavController by lazy { createNavHost().navController }
    lateinit var component: ApplicationComponent

    init {
        println("Activity was created")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = (applicationContext as App).appComponent

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun createNavHost(): NavHostFragment {
        return supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}