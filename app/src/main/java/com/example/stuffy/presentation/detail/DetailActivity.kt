package com.example.stuffy.presentation.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stuffy.R

import com.example.stuffy.core.domain.model.User

import com.example.stuffy.core.ui.ListUserAdapter
import com.example.stuffy.databinding.ActivityDetailBinding

import com.example.stuffy.presentation.main.MainActivity

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val list = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView14.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }
        list.addAll(listHeroes)
        showRecyclerList()
        binding.recyclerView3.setHasFixedSize(true)
    }

    private fun showRecyclerList() {
        binding.recyclerView3.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)
        val listHeroAdapter = ListUserAdapter(list)
        binding.recyclerView3.adapter = listHeroAdapter
        listHeroAdapter.onItemClick ={
            showSelectedUser(it)
        }
}
        private fun showSelectedUser(hero: User) {
            Toast.makeText(this, "Kamu memilih " + hero.username, Toast.LENGTH_SHORT).show()
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

        companion object {
        const val DATA = "data"
    }

}