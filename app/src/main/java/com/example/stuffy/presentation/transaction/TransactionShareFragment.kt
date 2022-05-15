package com.example.stuffy.presentation.transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stuffy.R
import com.example.stuffy.core.domain.model.ConfirmationTransaction
import com.example.stuffy.core.domain.model.Share
import com.example.stuffy.core.ui.ConfirmationAdapter
import com.example.stuffy.core.ui.ShareAdapter
import com.example.stuffy.databinding.FragmentTransactionConfirmationBinding
import com.example.stuffy.databinding.FragmentTransactionShareBinding


class TransactionShareFragment : Fragment() {
    private var _binding: FragmentTransactionShareBinding? = null
    private val list = ArrayList<Share>()

    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentTransactionShareBinding.inflate(inflater, container, false)
        binding?.recyclerview?.setHasFixedSize(true)
        list.addAll(listConfirmation)
        showRecyclerList()
        return binding?.root
    }
    private val listConfirmation: ArrayList<Share>
        get() {

            val dataPhoto = resources.obtainTypedArray(R.array.image)

            val dataName= resources.getStringArray(R.array.name)
            val dataTaker= resources.getStringArray(R.array.name)
            val dataStatus= resources.getStringArray(R.array.status)
            val dataDescription= resources.getStringArray(R.array.location)
            val listHero = ArrayList<Share>()
            for (i in dataName.indices) {
                val hero = Share(
                    dataPhoto.getResourceId(i, -1),
                    dataName[i],
                    dataDescription[i],
                    dataStatus[i],
                    dataTaker[i],
                )
                listHero.add(hero)
            }
            dataPhoto.recycle()
            return listHero
        }

    private fun showRecyclerList() {
        binding?.recyclerview?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
        val listHeroAdapter = ShareAdapter(list)
        binding?.recyclerview?.adapter = listHeroAdapter
        listHeroAdapter.onItemClick = {
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