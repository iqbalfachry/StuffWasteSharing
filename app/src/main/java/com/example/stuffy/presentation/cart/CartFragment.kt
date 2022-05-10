package com.example.stuffy.presentation.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.stuffy.databinding.FragmentCartBinding


class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {


        _binding = FragmentCartBinding.inflate(inflater, container, false)


        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}