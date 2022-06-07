package com.example.stuffy.presentation.transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stuffy.R
import com.example.stuffy.core.data.Resource

import com.example.stuffy.core.domain.model.Share
import com.example.stuffy.core.ui.ConfirmationAdapter

import com.example.stuffy.core.ui.ShareAdapter

import com.example.stuffy.databinding.FragmentTransactionShareBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class TransactionShareFragment : Fragment() {
    private var _binding: FragmentTransactionShareBinding? = null
    private val transactionShareViewModel : TransactionShareViewModel by viewModel()
    private lateinit var shareAdapter: ShareAdapter
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentTransactionShareBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.recyclerview?.setHasFixedSize(true)
        transactionShareViewModel.transaction.observe(viewLifecycleOwner){
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {

                        binding?.recyclerview?.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        binding?.recyclerview?.visibility = View.VISIBLE

                        it.data?.let { it1 -> shareAdapter.setData(it1) }

                    }
                    is Resource.Error -> {
                        binding?.recyclerview?.visibility = View.VISIBLE
                        Toast.makeText(activity,it.message,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        showRecyclerList()
    }

    private fun showRecyclerList() {
        binding?.recyclerview?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
       shareAdapter = ShareAdapter()
        binding?.recyclerview?.adapter = shareAdapter
        shareAdapter.onItemClick = {
            showSelectedUser(it)
        }
    }
    private fun showSelectedUser(hero: Share) {
        Toast.makeText(activity, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}