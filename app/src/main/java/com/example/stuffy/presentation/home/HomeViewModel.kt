package com.example.stuffy.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.stuffy.core.domain.useCase.StuffyUseCase

class HomeViewModel(stuffyUseCase: StuffyUseCase) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    val movie = stuffyUseCase.getListMovie().asLiveData()
    val category = stuffyUseCase.getCategory().asLiveData()
}