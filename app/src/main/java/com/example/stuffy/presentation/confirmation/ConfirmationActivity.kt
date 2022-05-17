package com.example.stuffy.presentation.confirmation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.stuffy.databinding.ActivityConfirmationBinding
import com.example.stuffy.presentation.share.ShareViewModel


class ConfirmationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmationBinding
    private val confirmationViewModel : ConfirmationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.count.text = confirmationViewModel.count.value.toString()
        binding.inc.setOnClickListener{
            confirmationViewModel.inc()
            binding.count.text = confirmationViewModel.count.value.toString()
        }
        binding.dec.setOnClickListener{
            confirmationViewModel.dec()
            binding.count.text = confirmationViewModel.count.value.toString()
        }
    }
}