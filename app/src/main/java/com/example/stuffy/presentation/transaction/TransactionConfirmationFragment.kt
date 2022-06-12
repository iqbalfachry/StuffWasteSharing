package com.example.stuffy.presentation.transaction

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stuffy.R
import com.example.stuffy.core.data.Resource

import com.example.stuffy.core.domain.model.ConfirmationTransaction

import com.example.stuffy.core.ui.ConfirmationAdapter
import com.example.stuffy.core.ui.ListProductAdapter
import com.example.stuffy.databinding.FragmentTransactionConfirmationBinding
import com.example.stuffy.presentation.share.ShareViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class TransactionConfirmationFragment : Fragment() {

    private var _binding: FragmentTransactionConfirmationBinding? = null
    private val transactionConfirmationViewModel : TransactionConfirmationViewModel by viewModel()
    private val binding get() = _binding
    private lateinit var confirmationAdapter: ConfirmationAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTransactionConfirmationBinding.inflate(inflater, container, false)

        return binding?.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.recyclerview?.setHasFixedSize(true)
        showRecyclerList()
        transactionConfirmationViewModel.transaction.observe(viewLifecycleOwner){
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {

                        binding?.recyclerview?.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        binding?.recyclerview?.visibility = View.VISIBLE

                        it.data?.let { it1 -> confirmationAdapter.setData(it1) }

                    }
                    is Resource.Error -> {
                        binding?.recyclerview?.visibility = View.VISIBLE
                        Toast.makeText(activity,it.message,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


    }


    private fun showRecyclerList() {
        binding?.recyclerview?.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL,false)
        confirmationAdapter = ConfirmationAdapter()
        binding?.recyclerview?.adapter = confirmationAdapter
        confirmationAdapter.onItemClick ={
            showSelectedUser(it)
        }
        confirmationAdapter.onButtonClick={


                    val mCategoryFragment = TransactionConfirmationDetailFragment()
                    val mFragmentManager = parentFragmentManager
            val mBundle = Bundle()

                mBundle.putParcelable(
                    TransactionConfirmationDetailFragment.EXTRA_NAME,
                    it
                )

                mCategoryFragment.arguments = mBundle

                mFragmentManager.beginTransaction().apply {
                    replace(
                        R.id.frame,
                        mCategoryFragment,
                        TransactionConfirmationDetailFragment::class.java.simpleName
                    )
                    addToBackStack("home")
                    commit()
                }


        }




    }

    private fun showSelectedUser(hero: ConfirmationTransaction) {
        Toast.makeText(activity, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}