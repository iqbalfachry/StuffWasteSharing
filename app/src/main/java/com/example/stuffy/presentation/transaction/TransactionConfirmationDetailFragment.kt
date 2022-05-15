package com.example.stuffy.presentation.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stuffy.R
import com.example.stuffy.core.domain.model.ConfirmationTransaction
import com.example.stuffy.core.ui.ConfirmationAdapter
import com.example.stuffy.core.ui.ConfirmationDetailAdapter
import com.example.stuffy.databinding.FragmentTransactionConfirmationDetailBinding

class TransactionConfirmationDetailFragment : Fragment() {
    private var _binding: FragmentTransactionConfirmationDetailBinding? = null
    private val list = ArrayList<ConfirmationTransaction>()

    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        // Inflate the layout for this fragment
        _binding = FragmentTransactionConfirmationDetailBinding.inflate(inflater, container, false)

        return binding?.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.recyclerview?.setHasFixedSize(true)
        list.addAll(listConfirmation)
        showRecyclerList()
        if (savedInstanceState != null) {
            val descFromBundle = savedInstanceState.getString(EXTRA_DESCRIPTION)

        }
        if (arguments != null) {
            val categoryName = arguments?.getString(EXTRA_NAME)

        }

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
        val listHeroAdapter = ConfirmationDetailAdapter(list)
        binding?.recyclerview?.adapter = listHeroAdapter

        }
    companion object {
        var EXTRA_NAME = "extra_name"
        var EXTRA_DESCRIPTION = "extra_description"
    }


}
