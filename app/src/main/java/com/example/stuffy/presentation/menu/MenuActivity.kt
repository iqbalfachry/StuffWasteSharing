package com.example.stuffy.presentation.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.stuffy.R
import com.example.stuffy.databinding.ActivityMenuBinding
import com.example.stuffy.presentation.main.MainActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            Glide.with(this@MenuActivity)
                .load(R.drawable.user1)
                .circleCrop()
                .into(imageView3)
        }
        binding.imageView2.setOnClickListener {
            Intent(this@MenuActivity, MainActivity::class.java).also{
                startActivity(it)
            }



        }
    }
}