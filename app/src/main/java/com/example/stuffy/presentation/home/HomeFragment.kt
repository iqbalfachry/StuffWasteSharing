package com.example.stuffy.presentation.home



import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

import androidx.fragment.app.Fragment


import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.stuffy.R
import com.example.stuffy.core.data.Resource
import com.example.stuffy.core.domain.model.Filter
import com.example.stuffy.core.domain.model.Product
import com.example.stuffy.core.ui.FilterAdapter

import com.example.stuffy.core.ui.ListProductAdapter
import com.example.stuffy.databinding.FragmentHomeBinding
import com.example.stuffy.presentation.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val filter = ArrayList<Filter>()
    private val binding get() = _binding
    private val movieViewModel: HomeViewModel by viewModel()
    private lateinit var movieAdapter: ListProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.imageView?.setOnClickListener {it->
            it.findNavController().navigate(R.id.action_navigation_home_to_menuActivity2)
        }
        movieAdapter = ListProductAdapter()

        activity?.let { it ->
            movieViewModel.movie.observe(it) {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            it.data?.let { it1 -> movieAdapter.setData(it1) }
                        }
                        is Resource.Error -> {


                        }
                    }
                }
            }
        }
        showRecyclerList()


        filter.addAll(listFilter)
        showRecyclerListFilter()
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
        binding?.recyclerView?.setHasFixedSize(true)
        val listHeroAdapter = FilterAdapter(filter)
        binding?.recyclerView?.adapter = listHeroAdapter
        listHeroAdapter.onItemClick ={
            showSelectedUser(it)
        }




    }

    private fun showSelectedUser(hero: Filter) {
        Toast.makeText(activity, "Kamu memilih " + hero.filterName, Toast.LENGTH_SHORT).show()
    }


    private fun showRecyclerList() {
        binding?.rvHeroes?.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding?.rvHeroes?.setHasFixedSize(true)
        binding?.rvHeroes?.adapter = movieAdapter
        movieAdapter.onItemClick={
                product ->
            Intent(activity, DetailActivity::class.java).also {
                it.putExtra(DetailActivity.DATA, product)
                startActivity(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        filter.clear()

    }
}