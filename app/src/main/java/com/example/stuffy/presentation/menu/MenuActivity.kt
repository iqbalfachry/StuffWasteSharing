package com.example.stuffy.presentation.menu

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.navigation.ActivityNavigator
import com.bumptech.glide.Glide
import com.example.stuffy.R
import com.example.stuffy.core.utils.EXTRA_CREDENTIAL
import com.example.stuffy.core.utils.TAG
import com.example.stuffy.databinding.ActivityMenuBinding

import com.example.stuffy.presentation.settings.SettingsActivity
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    private lateinit var oneTapClient: SignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        oneTapClient = Identity.getSignInClient(this)
        val credential = intent.extras?.get(EXTRA_CREDENTIAL) as SignInCredential
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
        binding.signout.setOnClickListener {
            signOut()
        }

        binding.pengaturan.setOnClickListener {
            Intent(this@MenuActivity, SettingsActivity::class.java).also {
                startActivity(it)
            }
        }
    }
    private fun signOut() {
        oneTapClient
            .signOut()
            .addOnSuccessListener {
                Log.d(TAG, "Success sign out")
                finish()
            }
            .addOnFailureListener { e ->
                Log.d(TAG, e.localizedMessage ?: "null")
            }

    }
    override fun onBackPressed() {
        super.onBackPressed()
        ActivityNavigator.applyPopAnimationsToPendingTransition(this)
    }
}
