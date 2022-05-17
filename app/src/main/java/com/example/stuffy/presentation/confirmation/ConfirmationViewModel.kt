package com.example.stuffy.presentation.confirmation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConfirmationViewModel:  ViewModel() {
    private val _count = MutableLiveData<Int>().apply {
        value = 1
    }


    val count: LiveData<Int> = _count
    fun inc() {
        _count.value?.let {
            _count.value = it + 1
        }
    }

    fun dec() {
        _count.value?.let {
            if (_count.value != 1) {
                _count.value = it - 1
            }

        }
    }

}