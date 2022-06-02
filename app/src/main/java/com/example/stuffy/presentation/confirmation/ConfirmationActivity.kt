package com.example.stuffy.presentation.confirmation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.stuffy.core.domain.model.Product
import com.example.stuffy.databinding.ActivityConfirmationBinding
import com.example.stuffy.presentation.detail.DetailActivity
import com.example.stuffy.presentation.main.MainActivity
import com.example.stuffy.presentation.share.ShareViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ConfirmationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmationBinding
    private val confirmationViewModel : ConfirmationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailProduct = intent.getParcelableExtra<Product>(DetailActivity.DATA)
        showDetailMovie(detailProduct)
        binding.count.text = confirmationViewModel.count.value.toString()
        binding.inc.setOnClickListener{
            confirmationViewModel.inc()
            binding.count.text = confirmationViewModel.count.value.toString()
        }
        binding.imageView18.setOnClickListener {
            Intent(this, DetailActivity::class.java).also {
                it.putExtra(DetailActivity.DATA, detailProduct)
                startActivity(it)
            }
        }
        binding.dec.setOnClickListener{
            confirmationViewModel.dec()
            binding.count.text = confirmationViewModel.count.value.toString()
        }

    }
    private fun showDetailMovie(detailMovie: Product?) {
        detailMovie?.let {
            with(binding) {
                Glide.with(this@ConfirmationActivity)
                    .load(detailMovie.avatar)
                    .transform(RoundedCorners(20))
                    .into(imageView20)
textView37.text = detailMovie.name
                textView38.text = detailMovie.location
                textView40.text = detailMovie.location

            }
        }
    }
    companion object {
        const val DATA = "data"
    }
}