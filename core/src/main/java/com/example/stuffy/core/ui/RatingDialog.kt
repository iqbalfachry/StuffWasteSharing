package com.example.stuffy.core.ui

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

import android.view.Window
import android.widget.Button
import android.widget.ImageView

import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.stuffy.core.R
import com.example.stuffy.core.domain.model.ConfirmationTaker
import com.example.stuffy.core.domain.model.Take

class RatingDialog {
    fun showDialog(activity: Activity?, msg: String?,data: Take) {
        val dialog = activity?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(false)
        dialog?.setContentView(R.layout.rating_dialog)
        val text = dialog?.findViewById(R.id.textModal) as TextView
        val name= dialog.findViewById(R.id.textView6) as TextView
        val location = dialog.findViewById(R.id.textView7) as TextView
        val image = dialog.findViewById(R.id.imageView4) as ImageView
        dialog.setCanceledOnTouchOutside(true)
        text.text = msg
        name.text = data.name
        location.text=data.location
        Glide.with(activity)
            .load(data.image)
            .into(image)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val dialogButton: Button = dialog.findViewById(R.id.pesan) as Button
        dialogButton.setOnClickListener {
            Toast.makeText(activity,"Berhasil dikirim",Toast.LENGTH_SHORT).show()
            dialog.dismiss()

        }
        dialog.show()
    }
}