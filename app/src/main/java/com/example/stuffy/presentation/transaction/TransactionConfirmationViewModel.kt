package com.example.stuffy.presentation.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.stuffy.core.domain.useCase.StuffyUseCase


class TransactionConfirmationViewModel(private val stuffyUseCase: StuffyUseCase):  ViewModel() {
   fun transaction(email: String) =
        stuffyUseCase.getTransactions(email).asLiveData()
}