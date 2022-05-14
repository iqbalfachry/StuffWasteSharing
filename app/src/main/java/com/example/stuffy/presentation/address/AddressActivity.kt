package com.example.stuffy.presentation.address

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stuffy.databinding.ActivityAddressBinding
import com.example.stuffy.databinding.ActivityConfirmationBinding

class AddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}