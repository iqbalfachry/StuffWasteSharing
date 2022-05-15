package com.example.stuffy.core.ui

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.example.stuffy.core.R

class RatingDialog {
    fun showDialog(activity: Activity?, msg: String?) {
        val dialog = activity?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(false)
        dialog?.setContentView(R.layout.rating_dialog)
        val text = dialog?.findViewById(R.id.textModal) as TextView
        text.text = msg
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val dialogButton: Button = dialog.findViewById(R.id.pesan) as Button
        dialogButton.setOnClickListener {

            dialog.dismiss()

        }
        dialog.show()
    }
}