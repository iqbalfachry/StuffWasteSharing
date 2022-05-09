package com.example.stuffy.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stuffy.databinding.ActivityDetailBinding
import com.example.stuffy.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}