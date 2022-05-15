package com.example.stuffy.presentation.detail

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.stuffy.R

import com.example.stuffy.core.domain.model.Product
import com.example.stuffy.core.ui.ListOtherProductAdapter

import com.example.stuffy.core.ui.ListProductAdapter
import com.example.stuffy.databinding.ActivityDetailBinding
import com.example.stuffy.presentation.confirmation.ConfirmationActivity

import com.example.stuffy.presentation.main.MainActivity

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val list = ArrayList<Product>()
    @RequiresApi(Build.VERSION_CODES.O)
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
        val detailProduct = intent.getParcelableExtra<Product>(DATA)
        showDetailMovie(detailProduct)
        binding.button3.setOnClickListener {
            Intent(this, ConfirmationActivity::class.java).also {
                startActivity(it)
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun showDetailMovie(detailMovie: Product?) {
        detailMovie?.let {
            with(binding) {
                Glide.with(this@DetailActivity)
                    .load(detailMovie.avatar)
                    .transform(RoundedCorners(20))
                    .into(imageView13)

                productname.text = detailMovie.name
               detail.text =  detailMovie.description
                textView16.text = detailMovie.location
            }
        }
    }
    private fun showRecyclerList() {
        binding.recyclerView3.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)
        val listHeroAdapter = ListOtherProductAdapter(list)
        binding.recyclerView3.adapter = listHeroAdapter
        listHeroAdapter.onItemClick ={
            showSelectedUser(it)
        }
}
        private fun showSelectedUser(hero: Product) {
            Toast.makeText(this, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
        }
        private val listHeroes: ArrayList<Product>
        get() {

            val dataName = resources.getStringArray(R.array.name)
            val dataPhoto = resources.obtainTypedArray(R.array.avatar)

            val dataDescription = resources.getStringArray(R.array.description)
            val dataLocation = resources.getStringArray(R.array.location)

            val listHero = ArrayList<Product>()
            for (i in dataName.indices) {
                val hero = Product(
                    dataName[i],
                    dataPhoto.getResourceId(i, -1),
                    dataLocation[i],
dataDescription[i],
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