package com.example.stuffy.presentation.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.stuffy.core.domain.useCase.StuffyUseCase


class TransactionConfirmationViewModel(stuffyUseCase: StuffyUseCase):  ViewModel() {
   val transaction =
        stuffyUseCase.getTransactions().asLiveData()
}