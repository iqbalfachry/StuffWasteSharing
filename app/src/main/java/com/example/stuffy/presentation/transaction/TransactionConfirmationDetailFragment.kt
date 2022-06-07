package com.example.stuffy.presentation.transaction

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.stuffy.core.data.Resource
import com.example.stuffy.core.domain.model.ConfirmationTransaction
import com.example.stuffy.core.ui.ConfirmationDetailAdapter
import com.example.stuffy.databinding.FragmentTransactionConfirmationDetailBinding
import com.example.stuffy.presentation.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TransactionConfirmationDetailFragment : Fragment() {
    private var _binding: FragmentTransactionConfirmationDetailBinding? = null
    private lateinit var confirmationAdapter: ConfirmationDetailAdapter
    private val transactionConfirmationDetailViewModel: TransactionConfirmationDetailViewModel by viewModel()
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTransactionConfirmationDetailBinding.inflate(inflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.recyclerview?.setHasFixedSize(true)


        val data = arguments?.getParcelable<ConfirmationTransaction>(EXTRA_NAME)
        if (data != null) {
            showRecyclerList(data)
        }
        data?.confirmation?.let { confirmationAdapter.setData(it) }
        binding?.imageView5?.setOnClickListener {
            val fm: FragmentManager? = activity?.supportFragmentManager
            fm?.popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE)


        }

        if (arguments != null) {

            with(binding) {
                activity?.let {
                    this?.imageView5?.let { it1 ->
                        Glide.with(it)
                            .load(data?.image)
                            .transform(RoundedCorners(20))
                            .into(it1)
                    }

                }
                binding?.textView10?.text = data?.name
                binding?.textView11?.text =
                    StringBuilder().append(data?.size).append(" orang ingin ambil barang ini")
            }


        }


    }

    private fun showRecyclerList(data:ConfirmationTransaction) {
        binding?.recyclerview?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
        confirmationAdapter = ConfirmationDetailAdapter()
        binding?.recyclerview?.adapter = confirmationAdapter
        confirmationAdapter.onButtonAcceptClick = { it ->
            transactionConfirmationDetailViewModel.updateConfirmationStatus(it.confirmationId, "Diterima")
                .observe(viewLifecycleOwner) {
                    if (it != null) {
                        when (it) {
                            is Resource.Loading -> {


                            }
                            is Resource.Success -> {
                                transactionConfirmationDetailViewModel.updateTransactionStatus(data.id,"Selesai").observe(
                                    viewLifecycleOwner
                                ){
                                    if (it != null) {
                                        when (it) {
                                            is Resource.Loading -> {


                                            }
                                            is Resource.Success -> {




                                            }
                                            is Resource.Error -> {

                                            }
                                        }
                                    }
                                }
                                Toast.makeText(activity, "Accepted", Toast.LENGTH_SHORT).show()
                                Intent(
                                    activity,
                                    MainActivity::class.java
                                ).also { intent ->
                                    startActivity(intent)
                                }
                                activity?.overridePendingTransition(0, 0);

                            }
                            is Resource.Error -> {
                                binding?.recyclerview?.visibility = View.VISIBLE
                                Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                            }
                        }
                    }
                }
        }

        confirmationAdapter.onButtonRejectClick = { it ->
            transactionConfirmationDetailViewModel.updateConfirmationStatus(it.confirmationId, "Ditolak")
                .observe(viewLifecycleOwner) {
                    if (it != null) {
                        when (it) {
                            is Resource.Loading -> {


                            }
                            is Resource.Success -> {

                                Toast.makeText(activity, "rejected", Toast.LENGTH_SHORT).show()
                                Intent(
                                    activity,
                                    MainActivity::class.java
                                ).also { intent ->
                                    startActivity(intent)
                                }
                                activity?.overridePendingTransition(0, 0);

                            }
                            is Resource.Error -> {
                                binding?.recyclerview?.visibility = View.VISIBLE
                                Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        var EXTRA_NAME = "extra_name"

    }
}
