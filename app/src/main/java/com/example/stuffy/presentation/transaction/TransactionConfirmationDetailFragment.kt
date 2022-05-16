package com.example.stuffy.presentation.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.stuffy.R
import com.example.stuffy.core.domain.model.ConfirmationTaker
import com.example.stuffy.core.domain.model.ConfirmationTransaction
import com.example.stuffy.core.ui.ConfirmationDetailAdapter
import com.example.stuffy.databinding.FragmentTransactionConfirmationDetailBinding

class TransactionConfirmationDetailFragment : Fragment() {
    private var _binding: FragmentTransactionConfirmationDetailBinding? = null
    private val list = ArrayList<ConfirmationTaker>()

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
        list.addAll(listConfirmation)
        showRecyclerList()

        if (arguments != null) {
            val data = arguments?.getParcelable<ConfirmationTransaction>(EXTRA_NAME)
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
                binding?.textView11?.text = StringBuilder().append(data?.size).append(" orang ingin ambil barang ini")
            }


        }


        }
    private val listConfirmation: ArrayList<ConfirmationTaker>
        get() {

            val dataPhoto = resources.obtainTypedArray(R.array.image)

            val dataName= resources.getStringArray(R.array.name)
            val dataDescription= resources.getStringArray(R.array.note)
            val listHero = ArrayList<ConfirmationTaker>()
            for (i in dataName.indices) {
                val hero = ConfirmationTaker(
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



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        var EXTRA_NAME = "extra_name"

    }
}
