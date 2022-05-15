package com.example.stuffy.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stuffy.R
import com.example.stuffy.core.domain.model.Favorite
import com.example.stuffy.core.domain.model.Share
import com.example.stuffy.core.ui.FavoriteAdapter
import com.example.stuffy.favorite.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favorite = ArrayList<Favorite>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView21.setOnClickListener {
            finish()
        }
        binding.recyclerview.setHasFixedSize(true)
        favorite.addAll(listCart)
        showRecyclerList()
    }
    private val listCart: ArrayList<Favorite>
        get() {

            val dataPhoto = resources.obtainTypedArray(R.array.image)

            val dataName= resources.getStringArray(R.array.name)
            val dataDescription= resources.getStringArray(R.array.location)
            val listHero = ArrayList<Favorite>()
            for (i in dataName.indices) {
                val hero = Favorite(
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
        binding.recyclerview.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        val listHeroAdapter = FavoriteAdapter(favorite)
        binding.recyclerview.adapter = listHeroAdapter
        listHeroAdapter.onItemClick ={
            showSelectedUser(it)
        }




    }

    private fun showSelectedUser(hero:Favorite) {
        Toast.makeText(this, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }
}