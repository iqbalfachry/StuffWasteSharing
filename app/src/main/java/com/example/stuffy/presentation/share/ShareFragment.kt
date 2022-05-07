package com.example.stuffy.presentation.share

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.stuffy.databinding.FragmentNotificationsBinding
import com.example.stuffy.databinding.FragmentShareBinding
import com.example.stuffy.presentation.notifications.NotificationsViewModel

class ShareFragment :  Fragment() {

    private var _binding: FragmentShareBinding? = null


    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        val shareViewModel =
            ViewModelProvider(this)[ShareViewModel::class.java]

        _binding = FragmentShareBinding.inflate(inflater, container, false)
        val root: ConstraintLayout? = binding?.root

        val textView: TextView? = binding?.textShare
        shareViewModel.text.observe(viewLifecycleOwner) {
            textView?.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}