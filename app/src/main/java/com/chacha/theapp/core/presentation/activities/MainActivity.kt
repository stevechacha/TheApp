package com.chacha.theapp.core.presentation.activities

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.chacha.theapp.R
import com.chacha.theapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupBottomNavigation()
        binding.bottomNavigation.background = null



    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.apply {
            val navView: BottomNavigationView = findViewById(R.id.bottomNavigation)
            itemIconTintList = null
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
            navHostFragment.navController.apply {
                setupWithNavController(this,)
                addOnDestinationChangedListener { _, _, args ->
                    // Top level items should have such argument with value set to true
                    isVisible = args?.getBoolean("hasBottomNav", false) == true
                }
                setOnItemReselectedListener {
                    // Do nothing when selecting same item
                }
                // Set the bottom navigation view/bar background color

            }
        }
    }

}