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
import com.example.stuffy.presentation.main.MainActivity
import com.example.stuffy.presentation.termcondition.TermConditionActivity
import com.example.stuffy.presentation.transaction.TransactionConfirmationDetailFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val filter = ArrayList<Filter>()
    private val binding get() = _binding
    private val movieViewModel: HomeViewModel by viewModel()

    private lateinit var movieAdapter: ListProductAdapter
    private lateinit var auth: FirebaseAuth

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
        auth = Firebase.auth
        binding?.textView?.text = StringBuilder().append("Halo ").append(auth.currentUser?.displayName)
        binding?.imageView?.setOnClickListener { it ->
            it.findNavController().navigate(R.id.action_navigation_home_to_menuActivity2)
        }
binding?.refresh?.setOnRefreshListener {

    startActivity(Intent(activity, MainActivity::class.java))
    activity?.overridePendingTransition(0, 0);
}

        showRecyclerList()
        showRecyclerListFilter()
            movieViewModel.movie.observe(viewLifecycleOwner) {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> {
                            binding?.progressBar?.visibility = View.VISIBLE
                            binding?.recyclerView?.visibility = View.GONE
                        }
                        is Resource.Success -> {
                            binding?.recyclerView?.visibility = View.VISIBLE
                            binding?.progressBar?.visibility = View.GONE
                            it.data?.let { it1 -> movieAdapter.setData(it1) }

                        }
                        is Resource.Error -> {
                            binding?.recyclerView?.visibility = View.VISIBLE
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(activity,it.message,Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }





            movieViewModel.category.observe(viewLifecycleOwner) {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> {
                            binding?.progressBar?.visibility = View.VISIBLE
                            binding?.recyclerView?.visibility = View.GONE
                        }
                        is Resource.Success -> {
                            binding?.recyclerView?.visibility = View.VISIBLE
                            binding?.progressBar?.visibility = View.GONE
                            it.data?.let { it1 -> categoryAdapter.setData(it1) }

                        }
                        is Resource.Error -> {
                            binding?.recyclerView?.visibility = View.VISIBLE
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(activity,it.message,Toast.LENGTH_SHORT).show()

                        }
                    }
                }
            }


    }

    

    private fun showRecyclerListFilter() {
        binding?.recyclerView?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding?.recyclerView?.setHasFixedSize(true)
        categoryAdapter = FilterAdapter()
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
        movieAdapter = ListProductAdapter()
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