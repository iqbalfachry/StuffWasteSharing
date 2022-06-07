package com.example.stuffy.presentation.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.stuffy.core.domain.useCase.StuffyUseCase


class TransactionShareViewModel(stuffyUseCase: StuffyUseCase):  ViewModel() {
   val transaction =
        stuffyUseCase.getTransactionsShare().asLiveData()
}