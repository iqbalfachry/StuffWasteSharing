package com.example.stuffy.presentation.transaction

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stuffy.R
import com.example.stuffy.core.data.Resource
import com.example.stuffy.core.domain.model.ConfirmationTransaction
import com.example.stuffy.core.domain.model.Take
import com.example.stuffy.core.ui.*
import com.example.stuffy.databinding.FragmentTransactionConfirmationBinding
import com.example.stuffy.databinding.FragmentTransactionShareBinding
import com.example.stuffy.databinding.FragmentTransactionTakeBinding
import com.example.stuffy.presentation.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel


class TransactionTakeFragment : Fragment() {
    private var _binding: FragmentTransactionTakeBinding? = null
    private val transactionTakeViewModel : TransactionTakeViewModel by viewModel()
    private lateinit var takeAdapter: TakeAdapter
    private val binding get() = _binding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentTransactionTakeBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.recyclerview?.setHasFixedSize(true)
        auth = Firebase.auth
        auth.currentUser?.email?.let {
            transactionTakeViewModel.transaction(it).observe(viewLifecycleOwner){
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> {

                            binding?.recyclerview?.visibility = View.GONE
                        }
                        is Resource.Success -> {
                            binding?.recyclerview?.visibility = View.VISIBLE

                            it.data?.let { it1 -> takeAdapter.setData(it1) }

                        }
                        is Resource.Error -> {
                            binding?.recyclerview?.visibility = View.VISIBLE
                            Toast.makeText(activity,it.message,Toast.LENGTH_SHORT).show()
                        }
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
        takeAdapter = TakeAdapter()
        binding?.recyclerview?.adapter = takeAdapter
        takeAdapter.onItemClick = {
            showSelectedUser(it)
        }
        takeAdapter.onButtonRatingClick = {
            val alert = RatingDialog()
            alert.showDialog(activity,"Berikan ulasan dan rating anda")
        }
        takeAdapter.onButtonAmbilClick = {
            transactionTakeViewModel.updateTransactionStatus(it.id,"Akan diambil").observe(viewLifecycleOwner){
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {


                        }
                        is Resource.Error -> {

                        }
                    }
                    Toast.makeText(activity, "sedang diambil", Toast.LENGTH_SHORT).show()
                    Intent(
                        activity,
                        MainActivity::class.java
                    ).also { intent ->
                        startActivity(intent)
                    }
                    activity?.overridePendingTransition(0, 0)
                }
            }
        }
    }
    private fun showSelectedUser(hero: Take) {
        Toast.makeText(activity, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}