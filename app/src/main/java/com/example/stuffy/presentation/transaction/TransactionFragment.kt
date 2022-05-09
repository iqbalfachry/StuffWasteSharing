package com.example.stuffy.presentation.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stuffy.databinding.FragmentTransactionBinding


class TransactionFragment : Fragment() {

    private var _binding: FragmentTransactionBinding? = null


    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        val notificationsViewModel =
            ViewModelProvider(this)[TransactionViewModel::class.java]

        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        val root: ConstraintLayout? = binding?.root

        val textView: TextView? = binding?.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView?.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}