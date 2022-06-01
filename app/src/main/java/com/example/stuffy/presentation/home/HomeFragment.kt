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

    private lateinit var categoryAdapter: FilterAdapter
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
        binding?.imageView?.setOnClickListener { it ->
            it.findNavController().navigate(R.id.action_navigation_home_to_menuActivity2)
        }
        movieAdapter = ListProductAdapter()
        categoryAdapter = FilterAdapter()
        activity?.let { it ->
            movieViewModel.movie.observe(it) {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> {
                            binding?.progressBar?.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding?.progressBar?.visibility = View.GONE
                            it.data?.let { it1 -> movieAdapter.setData(it1) }
                        }
                        is Resource.Error -> {


                        }
                    }
                }
            }
        }
        showRecyclerList()


        activity?.let { it ->
            movieViewModel.category.observe(it) {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> {
                            binding?.progressBar?.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding?.progressBar?.visibility = View.GONE
                            it.data?.let { it1 -> categoryAdapter.setData(it1) }
                        }
                        is Resource.Error -> {


                        }
                    }
                }
            }
        }
        showRecyclerListFilter()
    }


    private fun showRecyclerListFilter() {
        binding?.recyclerView?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding?.recyclerView?.setHasFixedSize(true)
        binding?.recyclerView?.adapter = categoryAdapter
        categoryAdapter.onItemClick = {
            showSelectedUser(it)
        }


    }

    private fun showSelectedUser(hero: Filter) {
        Toast.makeText(activity, "Kamu memilih " + hero.filterName, Toast.LENGTH_SHORT).show()
    }


    private fun showRecyclerList() {
        binding?.rvHeroes?.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding?.rvHeroes?.setHasFixedSize(true)
        binding?.rvHeroes?.adapter = movieAdapter
        movieAdapter.onItemClick = { product ->
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