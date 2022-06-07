package com.example.stuffy.presentation.transaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.stuffy.core.domain.useCase.StuffyUseCase

class TransactionConfirmationDetailViewModel(private val stuffyUseCase: StuffyUseCase):  ViewModel() {
   fun updateConfirmationStatus(id:String,status:String) =
        stuffyUseCase.updateConfirmationStatus(id,status).asLiveData()
    fun updateTransactionStatus(id:String,status:String) =
        stuffyUseCase.updateTransactionStatus(id,status).asLiveData()
}