package com.example.stuffy.presentation.share

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.stuffy.databinding.FragmentShareBinding


class ShareFragment :  Fragment() {

    private var _binding: FragmentShareBinding? = null
    private val shareViewModel : ShareViewModel by viewModels()


    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {

        _binding = FragmentShareBinding.inflate(inflater, container, false)


        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.count?.text = shareViewModel.count.value.toString()
        binding?.inc?.setOnClickListener{
            shareViewModel.inc()
            binding?.count?.text = shareViewModel.count.value.toString()
        }
        binding?.dec?.setOnClickListener{
            shareViewModel.dec()
            binding?.count?.text = shareViewModel.count.value.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}