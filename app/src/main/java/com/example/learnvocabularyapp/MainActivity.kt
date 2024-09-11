package com.example.learnvocabularyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.learnvocabularyapp.R.id.navHostFragmentView
import com.example.learnvocabularyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var appBarConfiguration: AppBarConfiguration



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getNavController()


    }


    private fun getNavController(){
        val navHostFragmentView = supportFragmentManager.findFragmentById(navHostFragmentView) as NavHostFragment
        val navControllerView= navHostFragmentView.navController


        setSupportActionBar(binding.toolbar)
        appBarConfiguration = AppBarConfiguration(navControllerView.graph)
        setupActionBarWithNavController(navControllerView, appBarConfiguration)


        binding.bottomNav.setupWithNavController(navControllerView)


        navControllerView.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.choiceFragment -> {
                    supportActionBar?.show()
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                    supportActionBar?.setDisplayShowTitleEnabled(false)
                }
                R.id.wordsFragment2 -> {
                    supportActionBar?.show()
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    supportActionBar?.setDisplayShowHomeEnabled(true)
                    supportActionBar?.setDisplayShowTitleEnabled(false)
                }
                R.id.detailFragment -> {
                    supportActionBar?.show()
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    supportActionBar?.setDisplayShowHomeEnabled(true)
                    supportActionBar?.setDisplayShowTitleEnabled(false)
                }
                R.id.learnedFragment2 -> {
                    supportActionBar?.hide()

                }
                else -> supportActionBar?.show()
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragmentView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}