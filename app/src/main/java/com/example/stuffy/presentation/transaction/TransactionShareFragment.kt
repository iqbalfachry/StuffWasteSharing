package com.example.stuffy.presentation.transaction

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.stuffy.core.data.Resource

import com.example.stuffy.core.domain.model.Share


import com.example.stuffy.core.ui.ShareAdapter

import com.example.stuffy.databinding.FragmentTransactionShareBinding
import com.example.stuffy.presentation.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel


class TransactionShareFragment : Fragment() {
    private var _binding: FragmentTransactionShareBinding? = null
    private val transactionShareViewModel : TransactionShareViewModel by viewModel()
    private lateinit var shareAdapter: ShareAdapter
    private val binding get() = _binding
    private lateinit var auth: FirebaseAuth
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
        auth = Firebase.auth
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
        shareAdapter.onButtonAmbilClick={

            transactionShareViewModel.updateTransactionStatus(it.id, "Selesai")
                .observe(viewLifecycleOwner) {
                    if (it != null) {
                        when (it) {
                            is Resource.Loading -> {


                            }
                            is Resource.Success -> {


                            }
                            is Resource.Error -> {
                                binding?.recyclerview?.visibility = View.VISIBLE
                                Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                            }
                        }







                    }
                }
            it.taker?.filter { it.status=="Terkonfirmasi"}?.map {
                transactionShareViewModel.updateConfirmationStatus( it.confirmationId,"Selesai").observe(
                    viewLifecycleOwner
                ){ it1 ->
                    if (it1 != null) {
                        when (it1) {
                            is Resource.Loading -> {


                            }
                            is Resource.Success -> {


                            }
                            is Resource.Error -> {

                            }
                        }
                    }
                }
            }

            Toast.makeText(activity, "Transaksi selesai", Toast.LENGTH_SHORT).show()
            Intent(
                activity,
                MainActivity::class.java
            ).also { intent ->
                startActivity(intent)
            }
            activity?.overridePendingTransition(0, 0)
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