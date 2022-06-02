package com.example.stuffy.presentation.settings


import android.content.Intent
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
        binding.email.setOnClickListener {
            val alert = SettingsDialog()
            alert.showDialog(this,"Masukkan email baru anda")
        }
        binding.username.setOnClickListener {
            val alert = SettingsDialog()
            alert.showDialog(this,"Masukkan username baru anda")
        }
        binding.no.setOnClickListener {
            val alert = SettingsDialog()
            alert.showDialog(this,"Masukkan no hp baru anda")
        }
        binding.alamat.setOnClickListener{
            val alert = SettingsDialog()
            alert.showDialog(this,"Masukkan alamat baru anda")
        }
binding.back.setOnClickListener{
    finish()
}
    }
}