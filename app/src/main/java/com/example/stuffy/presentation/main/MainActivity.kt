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
    private val filter = ArrayList<Filter>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)


        navView.setupWithNavController(navController)
        binding.recyclerView.setHasFixedSize(true)

        filter.addAll(listHeroes)
        showRecyclerList()

    }
    private val listHeroes: ArrayList<Filter>
        get() {

            val dataPhoto = resources.obtainTypedArray(R.array.image)

            val dataFilter= resources.getStringArray(R.array.filterName)

            val listHero = ArrayList<Filter>()
            for (i in dataFilter.indices) {
                val hero = Filter(
                    dataPhoto.getResourceId(i, -1),
                    dataFilter[i],
                )
                listHero.add(hero)
            }
            dataPhoto.recycle()
            return listHero
        }

    private fun showRecyclerList() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val listHeroAdapter = FilterAdapter(filter)
        binding.recyclerView.adapter = listHeroAdapter
        listHeroAdapter.setOnItemClickCallback(object : FilterAdapter.OnItemClickCallback {


            override fun onItemClicked(data: Filter) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(hero: Filter) {
        Toast.makeText(this, "Kamu memilih " + hero.filterName, Toast.LENGTH_SHORT).show()
    }

        }
