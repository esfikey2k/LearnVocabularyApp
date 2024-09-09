package com.example.learnvocabularyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.learnvocabularyapp.R.id.navHostFragmentView
import com.example.learnvocabularyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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

        binding.bottomNav.setupWithNavController(navControllerView)

    }
}