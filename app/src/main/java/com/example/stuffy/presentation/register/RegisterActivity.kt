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
import android.view.View
import com.example.stuffy.R
import com.example.stuffy.databinding.ActivityRegisterBinding
import com.example.stuffy.databinding.ActivitySettingsBinding
import com.example.stuffy.presentation.login.LoginActivity
import com.example.stuffy.presentation.privacypolicy.PrivacyPolicyActivity
import com.example.stuffy.presentation.termcondition.TermConditionActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }
}