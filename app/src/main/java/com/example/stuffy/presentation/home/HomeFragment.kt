package com.example.stuffy.presentation.home



import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

import androidx.fragment.app.Fragment


import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.stuffy.R
import com.example.stuffy.core.domain.model.Filter
import com.example.stuffy.core.domain.model.Product
import com.example.stuffy.core.ui.FilterAdapter

import com.example.stuffy.core.ui.ListProductAdapter
import com.example.stuffy.databinding.FragmentHomeBinding
import com.example.stuffy.presentation.detail.DetailActivity


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val list = ArrayList<Product>()
    private val filter = ArrayList<Filter>()
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: ConstraintLayout? = binding?.root
        binding?.imageView?.setOnClickListener {
               it.findNavController().navigate(R.id.action_navigation_home_to_menuActivity2)
        }

     binding?.rvHeroes?.setHasFixedSize(true)

        list.addAll(listHeroes)
        showRecyclerList()
        binding?.recyclerView?.setHasFixedSize(true)

        filter.addAll(listFilter)
        showRecyclerListFilter()

        return root
    }
    private val listFilter: ArrayList<Filter>
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

    private fun showRecyclerListFilter() {
        binding?.recyclerView?.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        val listHeroAdapter = FilterAdapter(filter)
        binding?.recyclerView?.adapter = listHeroAdapter
        listHeroAdapter.onItemClick ={
            showSelectedUser(it)
        }




    }

    private fun showSelectedUser(hero: Filter) {
        Toast.makeText(activity, "Kamu memilih " + hero.filterName, Toast.LENGTH_SHORT).show()
    }
    private val listHeroes: ArrayList<Product>
        get() {

            val dataName = resources.getStringArray(R.array.name)
            val dataPhoto = resources.obtainTypedArray(R.array.avatar)


            val dataLocation = resources.getStringArray(R.array.location)

            val listHero = ArrayList<Product>()
            for (i in dataName.indices) {
                val hero = Product(

                    dataName[i],
                    dataPhoto.getResourceId(i, -1),
dataLocation[i],


                )
                listHero.add(hero)
            }
            dataPhoto.recycle()
            return listHero
        }

    private fun showRecyclerList() {
        binding?.rvHeroes?.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        val listHeroAdapter = ListProductAdapter(list)
        binding?.rvHeroes?.adapter = listHeroAdapter
        listHeroAdapter.onItemClick={
                movie ->
            Intent(activity, DetailActivity::class.java).also {
                it.putExtra(DetailActivity.DATA, movie)
                startActivity(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}