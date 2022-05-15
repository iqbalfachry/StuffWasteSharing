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

import com.example.stuffy.core.domain.model.ConfirmationTransaction

import com.example.stuffy.core.ui.ConfirmationAdapter
import com.example.stuffy.databinding.FragmentTransactionConfirmationBinding


class TransactionConfirmationFragment : Fragment() {

    private var _binding: FragmentTransactionConfirmationBinding? = null
    private val list = ArrayList<ConfirmationTransaction>()

    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FrameLayout? {
        // Inflate the layout for this fragment
        _binding = FragmentTransactionConfirmationBinding.inflate(inflater, container, false)

        return binding?.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.recyclerview?.setHasFixedSize(true)
        list.addAll(listConfirmation)
        showRecyclerList()
    }
    private val listConfirmation: ArrayList<ConfirmationTransaction>
        get() {

            val dataPhoto = resources.obtainTypedArray(R.array.image)

            val dataName= resources.getStringArray(R.array.name)
            val dataDescription= resources.getStringArray(R.array.size)
            val listHero = ArrayList<ConfirmationTransaction>()
            for (i in dataName.indices) {
                val hero = ConfirmationTransaction(
                    dataPhoto.getResourceId(i, -1),
                    dataName[i],
                    dataDescription[i],
                )
                listHero.add(hero)
            }
            dataPhoto.recycle()
            return listHero
        }

    private fun showRecyclerList() {
        binding?.recyclerview?.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL,false)
        val listHeroAdapter = ConfirmationAdapter(list)
        binding?.recyclerview?.adapter = listHeroAdapter
        listHeroAdapter.onItemClick ={
            showSelectedUser(it)
        }
        listHeroAdapter.onButtonClick={


                    val mCategoryFragment = TransactionConfirmationDetailFragment()
                    val mFragmentManager = parentFragmentManager
            val mBundle = Bundle()
            mBundle.putString(TransactionConfirmationDetailFragment.EXTRA_NAME, "Lifestyle")
            val description = "Kategori ini akan berisi produk-produk lifestyle"
            mCategoryFragment.arguments = mBundle
            mCategoryFragment.description = description
                    mFragmentManager.beginTransaction().apply {
                        replace(R.id.frame, mCategoryFragment,  TransactionConfirmationDetailFragment::class.java.simpleName)

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