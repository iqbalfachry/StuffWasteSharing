package com.example.stuffy.presentation.menu

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.navigation.ActivityNavigator
import com.bumptech.glide.Glide
import com.example.stuffy.R
import com.example.stuffy.databinding.ActivityMenuBinding

import com.example.stuffy.presentation.settings.SettingsActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            Glide.with(this@MenuActivity)
                .load(R.drawable.user7)
                .circleCrop()
                .into(imageView3)
        }
        binding.imageView2.setOnClickListener {
            finish()
                ActivityNavigator.applyPopAnimationsToPendingTransition(this)


        }
        val uri = Uri.parse("stuffy://favorite")

        binding.wishlist.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        binding.pengaturan.setOnClickListener {
            Intent(this@MenuActivity, SettingsActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityNavigator.applyPopAnimationsToPendingTransition(this)
    }
}
