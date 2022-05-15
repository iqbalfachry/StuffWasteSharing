package com.example.stuffy.presentation.settings


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.stuffy.R

import com.example.stuffy.core.ui.SettingsDialog

import com.example.stuffy.databinding.ActivitySettingsBinding


class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            Glide.with(this@SettingsActivity)
                .load(R.drawable.user7)
                .circleCrop()
                .into(imageView23)
        }
        binding.name.setOnClickListener {
            val alert = SettingsDialog()
            alert.showDialog(this,"Masukkan nama baru anda")
        }
binding.back.setOnClickListener{
    finish()
}
    }
}