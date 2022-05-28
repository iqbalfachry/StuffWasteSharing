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
import com.example.stuffy.presentation.login.LoginActivity
import com.example.stuffy.presentation.main.MainActivity

import com.example.stuffy.presentation.settings.SettingsActivity
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    private lateinit var oneTapClient: SignInClient
    private lateinit var auth: FirebaseAuth
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    private fun updateUI(firebaseAuth: FirebaseUser?){
        if(firebaseAuth== null) {
            val intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        oneTapClient = Identity.getSignInClient(this)
        auth = Firebase.auth
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
        Firebase.auth.signOut()
                Intent(this@MenuActivity, LoginActivity::class.java).also {
                    startActivity(it)

                }


    }
    override fun onBackPressed() {
        super.onBackPressed()
        ActivityNavigator.applyPopAnimationsToPendingTransition(this)
    }
}
