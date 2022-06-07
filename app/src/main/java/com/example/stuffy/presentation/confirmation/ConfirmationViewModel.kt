package com.example.stuffy.presentation.confirmation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.stuffy.core.domain.useCase.StuffyUseCase

class ConfirmationViewModel(private val stuffyUseCase: StuffyUseCase):  ViewModel() {

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
    fun createConfirmation(productId: String,
                           email: String,
                           status: String,note:String) =
        stuffyUseCase.createConfirmation(productId,email, status,note).asLiveData()
}