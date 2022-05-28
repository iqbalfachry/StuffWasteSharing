package com.example.stuffy.presentation.login

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.stuffy.R
import com.example.stuffy.core.utils.EXTRA_CREDENTIAL
import com.example.stuffy.databinding.ActivityLoginBinding
import com.example.stuffy.presentation.main.MainActivity

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var oneTapClient: SignInClient


    private var showOneTapUI = true
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        oneTapClient = Identity.getSignInClient(this)
        binding.button.setOnClickListener {
            signIn()
        }
    }
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
 updateUI(currentUser)
    }
    private fun updateUI(firebaseAuth: FirebaseUser?){
        if(firebaseAuth != null) {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }
    private fun createSignInRequest(onlyAuthorizedAccounts: Boolean): BeginSignInRequest =
        BeginSignInRequest.builder()
            .setPasswordRequestOptions(
                BeginSignInRequest.PasswordRequestOptions.builder()
                    .setSupported(true)
                    .build()
            )
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(getString(R.string.web_client_id))
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(onlyAuthorizedAccounts)
                    .build()
            )
            .build()

    private fun signIn(){
        val signInRequest = createSignInRequest(onlyAuthorizedAccounts = true)


        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener(this) {result->
                try {
                    launch(result)
                } catch (e: IntentSender.SendIntentException) {
                    Log.e(TAG, "Couldn't start One Tap UI: ${e.localizedMessage}")
                }
            }
            .addOnFailureListener(this) { e ->
                // No saved credentials found. Launch the One Tap sign-up flow, or
                // do nothing and continue presenting the signed-out UI.
                e.localizedMessage?.let { Log.d(TAG, it) }
                signUp()
            }
    }
    private fun signUp() {
        val signUpRequest = createSignInRequest(onlyAuthorizedAccounts = false)

        oneTapClient
            .beginSignIn(signUpRequest)
            .addOnSuccessListener(this) { result ->
                try {
                    launch(result)
                } catch (e: IntentSender.SendIntentException) {
                    Log.e(TAG, "Couldn't start One Tap UI: ${e.localizedMessage}")
                }
            }
            .addOnFailureListener(this) { e ->
                e.localizedMessage?.let { Log.d(TAG, it) }
                // No saved credentials found. Show error
                e.localizedMessage?.let {
                    Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                }

            }
    }
    private fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        resultLauncher.launch(intent)
    }
    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {

                    try {
                        val credential = oneTapClient.getSignInCredentialFromIntent(result.data)
                        val idToken = credential.googleIdToken
                        val username = credential.id
                        val password = credential.password
                        when {
                            idToken != null -> {
                                // Got an ID token from Google. Use it to authenticate
                                // with your backend.

                                Log.d(TAG, "Got ID token.")
                            }
                            password != null -> {
                                // Got a saved username and password. Use them to authenticate
                                // with your backend.

                                Log.d(TAG, "Got password.")
                            }
                            else -> {
                                // Shouldn't happen.
                                Log.d(TAG, "No ID token or password!")
                            }
                        }
                        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                        auth.signInWithCredential(firebaseCredential)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithCredential:success")
                                    val user = auth.currentUser
                                  updateUI(user)
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                                    updateUI(null)
                                }
                            }


                    } catch (e: ApiException) {
                        when (e.statusCode) {
                            CommonStatusCodes.CANCELED -> {
                                Log.d(TAG, "One-tap dialog was closed.")
                                // Don't re-prompt the user.
                                showOneTapUI = false
                            }
                            CommonStatusCodes.NETWORK_ERROR -> {
                                Log.d(TAG, "One-tap encountered a network error.")
                                // Try again or just ignore.
                            }
                            else -> {
                                Log.d(TAG, "Couldn't get credential from result." +
                                        " (${e.localizedMessage})")
                            }
                        }
                    }



        }
    }


    }
