package com.example.stuffy.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stuffy.databinding.ActivityMenuBinding
import com.example.stuffy.favorite.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView21.setOnClickListener {
            finish()
        }
    }
}