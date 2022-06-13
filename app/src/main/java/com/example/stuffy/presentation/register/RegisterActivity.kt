package com.example.stuffy.presentation.register

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.stuffy.R
import com.example.stuffy.core.utils.TAG
import com.example.stuffy.databinding.ActivityRegisterBinding
import com.example.stuffy.databinding.ActivitySettingsBinding
import com.example.stuffy.presentation.login.LoginActivity
import com.example.stuffy.presentation.main.MainActivity
import com.example.stuffy.presentation.privacypolicy.PrivacyPolicyActivity
import com.example.stuffy.presentation.termcondition.TermConditionActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val string = SpannableString("Already a user? Login")
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText=false
                ds.color = Color.parseColor("#0C6B29")
            }


        }
        string.setSpan(clickableSpan, 16, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.textView55.text= string
        binding.textView55.movementMethod = LinkMovementMethod.getInstance()
        binding.textView55.highlightColor = Color.TRANSPARENT
        val string1 = SpannableString("I agree with the Terms and Condition and")
        val clickableSpan1: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                startActivity(Intent(this@RegisterActivity, TermConditionActivity::class.java))
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText=false
                ds.color = Color.parseColor("#0C6B29")
            }


        }
        string1.setSpan(clickableSpan1, 17, 36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.textView15.text= string1
        binding.textView15.movementMethod = LinkMovementMethod.getInstance()
        binding.textView15.highlightColor = Color.TRANSPARENT
        val string2 = SpannableString("Privacy Policy set by Stuffy")
        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                startActivity(Intent(this@RegisterActivity, PrivacyPolicyActivity::class.java))
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText=false
                ds.color = Color.parseColor("#0C6B29")
            }


        }
        string2.setSpan(clickableSpan2, 0, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.textView58.text= string2
        binding.textView58.movementMethod = LinkMovementMethod.getInstance()
        binding.textView58.highlightColor = Color.TRANSPARENT
        binding.button4.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
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
}