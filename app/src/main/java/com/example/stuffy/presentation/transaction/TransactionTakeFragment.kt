package com.example.stuffy.presentation.transaction

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stuffy.R
import com.example.stuffy.core.domain.model.ConfirmationTransaction
import com.example.stuffy.core.domain.model.Take
import com.example.stuffy.core.ui.ConfirmationAdapter
import com.example.stuffy.core.ui.RatingDialog
import com.example.stuffy.core.ui.SettingsDialog
import com.example.stuffy.core.ui.TakeAdapter
import com.example.stuffy.databinding.FragmentTransactionConfirmationBinding
import com.example.stuffy.databinding.FragmentTransactionShareBinding
import com.example.stuffy.databinding.FragmentTransactionTakeBinding


class TransactionTakeFragment : Fragment() {
    private var _binding: FragmentTransactionTakeBinding? = null
    private val list = ArrayList<Take>()

    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentTransactionTakeBinding.inflate(inflater, container, false)
        binding?.recyclerview?.setHasFixedSize(true)
        list.addAll(listConfirmation)
        showRecyclerList()
        return binding?.root
    }
    private val listConfirmation: ArrayList<Take>
        get() {

            val dataPhoto = resources.obtainTypedArray(R.array.image)

            val dataName= resources.getStringArray(R.array.name)
            val dataDescription= resources.getStringArray(R.array.location)
            val dataStatus= resources.getStringArray(R.array.status)
            val listHero = ArrayList<Take>()
            for (i in dataName.indices) {
                val hero = Take(
                    dataPhoto.getResourceId(i, -1),
                    dataName[i],
                    dataDescription[i],
                    dataStatus[i],
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
        val listHeroAdapter = TakeAdapter(list)
        binding?.recyclerview?.adapter = listHeroAdapter
        listHeroAdapter.onItemClick = {
            showSelectedUser(it)
        }
        listHeroAdapter.onButtonClick = {
            Log.d("buttonr",it.toString())
            val alert = RatingDialog()
            alert.showDialog(activity,"Berikan ulasan dan rating anda")
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