package com.example.stuffy.presentation.settings


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.stuffy.R

import com.example.stuffy.core.ui.SettingsDialog

import com.example.stuffy.databinding.ActivitySettingsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SettingsActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth =Firebase.auth
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            Glide.with(this@SettingsActivity)
                .load(auth.currentUser?.photoUrl)
                .circleCrop()
                .into(imageView23)
        }
        binding.textView50.text=auth.currentUser?.displayName
        binding.textView51.text=auth.currentUser?.displayName
        binding.textView52.text=if(auth.currentUser?.phoneNumber.equals("")) "no phone number" else auth.currentUser?.phoneNumber
        binding.textView53.text=auth.currentUser?.email
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