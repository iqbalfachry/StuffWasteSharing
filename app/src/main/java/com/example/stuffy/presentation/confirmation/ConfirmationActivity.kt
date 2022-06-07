package com.example.stuffy.presentation.confirmation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.stuffy.core.data.Resource
import com.example.stuffy.core.domain.model.Product
import com.example.stuffy.databinding.ActivityConfirmationBinding
import com.example.stuffy.presentation.detail.DetailActivity
import com.example.stuffy.presentation.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmationBinding
    private val confirmationViewModel : ConfirmationViewModel by viewModel()
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
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
                binding.button7.setOnClickListener{
                    val note =editTextTextPersonName.text.toString()
                    auth.currentUser?.email?.let { it1 ->
                        confirmationViewModel.createConfirmation(detailMovie.id,
                            it1,"Menunggu",note).observe(
                            this@ConfirmationActivity
                            ){
                            if (it != null) {
                                when (it) {
                                    is Resource.Loading -> {

                                    }
                                    is Resource.Success -> {


                                    }
                                    is Resource.Error -> {


                                    }
                                }
                            }
                        }
                        Toast.makeText(
                            this@ConfirmationActivity,
                            "product successfully taken wait for confirmation from sharer",
                            Toast.LENGTH_SHORT
                        ).show()

                        Intent(
                            this@ConfirmationActivity,
                            MainActivity::class.java
                        ).also { intent ->
                            startActivity(intent)
                        }
                        overridePendingTransition(0, 0);
                    }

                }
            }
        }
    }
    companion object {
        const val DATA = "data"
    }
}