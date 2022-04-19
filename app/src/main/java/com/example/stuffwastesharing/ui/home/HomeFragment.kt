package com.example.stuffwastesharing.ui.home

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stuffwastesharing.R
import com.example.stuffwastesharing.data.User
import com.example.stuffwastesharing.databinding.FragmentHomeBinding
import com.example.stuffwastesharing.ui.adapter.ListUserAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val list = ArrayList<User>()

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: ConstraintLayout? = binding?.root


     binding?.rvHeroes?.setHasFixedSize(true)

        list.addAll(listHeroes)
        showRecyclerList()
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding?.rvHeroes?.layoutManager = GridLayoutManager(activity, 2)
        } else {
            binding?.rvHeroes?.layoutManager = LinearLayoutManager(activity)
        }

        return root
    }
    private val listHeroes: ArrayList<User>
        get() {
            val dataName = resources.getStringArray(R.array.username)
            val dataDescription = resources.getStringArray(R.array.name)
            val dataPhoto = resources.obtainTypedArray(R.array.avatar)

            val dataFollower = resources.getStringArray(R.array.followers)
            val dataFollowing = resources.getStringArray(R.array.following)
            val dataCompany = resources.getStringArray(R.array.company)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataRepository = resources.getStringArray(R.array.repository)
            val listHero = ArrayList<User>()
            for (i in dataName.indices) {
                val hero = User(
                    dataName[i],
                    dataDescription[i],
                    dataPhoto.getResourceId(i, -1),
                    dataFollower[i],
                    dataFollowing[i],
                    dataCompany[i],
                    dataLocation[i],
                    dataRepository[i]
                )
                listHero.add(hero)
            }
            dataPhoto.recycle()
            return listHero
        }

    private fun showRecyclerList() {
        binding?.rvHeroes?.layoutManager = LinearLayoutManager(activity)
        val listHeroAdapter = ListUserAdapter(list)
        binding?.rvHeroes?.adapter = listHeroAdapter
        listHeroAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                showSelectedUser(data)

            }
        })
    }

    private fun showSelectedUser(hero: User) {
        Toast.makeText(activity, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}