package com.example.stuffy.presentation.confirmation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stuffy.databinding.ActivityConfirmationBinding


class ConfirmationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}