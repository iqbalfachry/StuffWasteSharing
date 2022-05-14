package com.example.stuffy.presentation.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stuffy.R
import com.example.stuffy.core.domain.model.Cart
import com.example.stuffy.core.ui.CartAdapter
import com.example.stuffy.databinding.FragmentCartBinding


class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val cart = ArrayList<Cart>()
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {


        _binding = FragmentCartBinding.inflate(inflater, container, false)

        binding?.recyclerView2?.setHasFixedSize(true)
        cart.addAll(listCart)
        showRecyclerList()
        return binding?.root
    }
    private val listCart: ArrayList<Cart>
        get() {

            val dataPhoto = resources.obtainTypedArray(R.array.image)

            val dataFilter= resources.getStringArray(R.array.filterName)
            val dataDescription= resources.getStringArray(R.array.location)
            val listHero = ArrayList<Cart>()
            for (i in dataFilter.indices) {
                val hero = Cart(
                    dataPhoto.getResourceId(i, -1),
                    dataFilter[i],
                    dataDescription[i],
                )
                listHero.add(hero)
            }
            dataPhoto.recycle()
            return listHero
        }

    private fun showRecyclerList() {
        binding?.recyclerView2?.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL,false)
        val listHeroAdapter = CartAdapter(cart)
        binding?.recyclerView2?.adapter = listHeroAdapter
        listHeroAdapter.onItemClick ={
            showSelectedUser(it)
        }




    }

    private fun showSelectedUser(hero:Cart) {
        Toast.makeText(activity, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}