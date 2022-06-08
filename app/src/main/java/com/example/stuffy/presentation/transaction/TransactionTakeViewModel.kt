package com.example.stuffy.presentation.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.stuffy.core.domain.useCase.StuffyUseCase

class TransactionTakeViewModel(private val stuffyUseCase: StuffyUseCase):  ViewModel() {
    fun transaction(email:String) =
        stuffyUseCase.getTransactionsTake(email).asLiveData()
    fun updateTransactionStatus(id:String,status:String) =
        stuffyUseCase.updateTransactionStatus(id,status).asLiveData()
}