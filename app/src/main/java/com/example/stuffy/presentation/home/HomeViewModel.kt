package com.example.stuffy.presentation.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.stuffy.core.domain.useCase.StuffyUseCase

class HomeViewModel(stuffyUseCase: StuffyUseCase) : ViewModel() {



    val movie = stuffyUseCase.getListMovie().asLiveData()
    val category = stuffyUseCase.getCategory().asLiveData()
}