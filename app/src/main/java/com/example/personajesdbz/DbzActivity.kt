package com.example.personajesdbz

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.personajesdbz.databinding.ActivityMainBinding
import com.example.personajesdbz.features.charactersdbz.viewmodel.CharactersDbzViewModel
import com.example.personajesdbz.features.charactersdbz.viewmodel.CharactersDbzViewModelFactory

class DbzActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null
    private var appBarConfiguration: AppBarConfiguration? = null
    val charactersDbzViewModel: CharactersDbzViewModel by viewModels {
        CharactersDbzViewModelFactory(
            (application as CharactersDbzApplication).charactersDbzRepository,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = navController?.let {
            AppBarConfiguration(it.graph)
        }

        appBarConfiguration?.let { appBarConfiguration ->
            navController?.let {
                setupActionBarWithNavController(it, appBarConfiguration)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return appBarConfiguration?.let {
            navController?.apply { navigateUp(it) }.run { return true }
        } == true
    }
}
