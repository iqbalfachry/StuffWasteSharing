package com.example.stuffy.presentation.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ActivityNavigator
import com.example.stuffy.core.ui.ViewDialog
import com.example.stuffy.databinding.ActivityMenuBinding
import com.example.stuffy.databinding.ActivitySettingsBinding
import com.example.stuffy.presentation.main.MainActivity
import com.example.stuffy.presentation.menu.MenuActivity

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