package com.example.stuffy.presentation.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions


import androidx.navigation.findNavController

import androidx.navigation.ui.setupWithNavController

import com.example.stuffy.R

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
        val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setEnterAnim(R.anim.slide_in_left)
            .setExitAnim(R.anim.slide_out_right)
            .setPopEnterAnim(R.anim.slide_in_right)
            .setPopExitAnim(R.anim.slide_out_left)
            .setPopUpTo(navController.graph.startDestinationId, true)
            .build()
        navView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.navigation_home,null,options)
                }
                R.id.navigation_dashboard -> {
                    navController.navigate(R.id.navigation_dashboard,null,options)
                }
                R.id.navigation_share -> {
                    navController.navigate(R.id.navigation_share,null,options)
                }
                R.id.navigation_notifications -> {
                    navController.navigate(R.id.navigation_notifications,null,options)
                }
            }
            true
        }


    }


        }
