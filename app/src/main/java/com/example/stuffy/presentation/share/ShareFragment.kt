package com.example.stuffy.presentation.share

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.ViewGroup

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.stuffy.databinding.FragmentShareBinding


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

binding?.count?.setText(shareViewModel.count.value.toString())
        binding?.inc?.setOnClickListener{
            shareViewModel.inc()
            binding?.count?.setText(shareViewModel.count.value.toString())
        }
        binding?.dec?.setOnClickListener{
            shareViewModel.dec()
            binding?.count?.setText(shareViewModel.count.value.toString())
        }
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}