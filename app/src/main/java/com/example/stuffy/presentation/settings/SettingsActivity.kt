package com.example.stuffy.presentation.settings


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.stuffy.core.ui.ViewDialog

import com.example.stuffy.databinding.ActivitySettingsBinding


class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.name.setOnClickListener {
            val alert = ViewDialog()
            alert.showDialog(this,"Masukkan nama baru anda")
        }
binding.back.setOnClickListener{
    finish()
}
    }
}