package com.example.stuffy.presentation.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stuffy.R
import com.example.stuffy.core.domain.model.Cart
import com.example.stuffy.core.ui.CartAdapter
import com.example.stuffy.databinding.ComingsoonBinding
import com.example.stuffy.databinding.FragmentCartBinding
import com.example.stuffy.presentation.confirmation.ConfirmationActivity



class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private var _binding1: ComingsoonBinding? = null
    private val cart = ArrayList<Cart>()
    private val binding get() = _binding
    private val binding1 get() = _binding1
    private var comingSoon = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {

        return if (comingSoon) {
            _binding1 = ComingsoonBinding.inflate(inflater, container, false)
            binding1?.root
        } else {
            _binding = FragmentCartBinding.inflate(inflater, container, false)
            binding?.root
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.recyclerView2?.setHasFixedSize(true)
        cart.addAll(listCart)
        showRecyclerList()
        binding?.pesan?.setOnClickListener {
            Intent(activity, ConfirmationActivity::class.java).also {
                startActivity(it)
            }
        }


    }

    private val listCart: ArrayList<Cart>
        get() {

            val dataPhoto = resources.obtainTypedArray(R.array.image)

            val dataName = resources.getStringArray(R.array.name)
            val dataDescription = resources.getStringArray(R.array.location)
            val listHero = ArrayList<Cart>()
            for (i in dataName.indices) {
                val hero = Cart(
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
        binding?.recyclerView2?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
        val listHeroAdapter = CartAdapter(cart)
        binding?.recyclerView2?.adapter = listHeroAdapter
        listHeroAdapter.onItemClick = {
            showSelectedUser(it)
        }


    }

    private fun showSelectedUser(hero: Cart) {
        Toast.makeText(activity, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _binding1 = null
    }
}