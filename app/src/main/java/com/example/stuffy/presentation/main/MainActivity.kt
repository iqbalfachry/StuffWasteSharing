package com.example.stuffy.presentation.main

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity


import androidx.navigation.findNavController

import androidx.navigation.ui.setupWithNavController

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stuffy.R
import com.example.stuffy.core.data.Filter

import com.example.stuffy.core.ui.FilterAdapter

import com.example.stuffy.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)


        navView.setupWithNavController(navController)


    }


        }
