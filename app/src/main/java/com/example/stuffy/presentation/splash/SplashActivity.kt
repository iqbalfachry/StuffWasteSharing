package com.example.stuffy.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.stuffy.databinding.ActivityMenuBinding
import com.example.stuffy.databinding.ActivitySplashBinding
import com.example.stuffy.presentation.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        Handler(mainLooper).postDelayed({
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }, 2000)
    }
}