package com.example.stuffy.di

import com.example.stuffy.core.domain.useCase.StuffyInteractor
import com.example.stuffy.core.domain.useCase.StuffyUseCase
import com.example.stuffy.presentation.confirmation.ConfirmationViewModel
import com.example.stuffy.presentation.home.HomeViewModel

import com.example.stuffy.presentation.share.ShareViewModel
import com.example.stuffy.presentation.transaction.TransactionConfirmationDetailViewModel
import com.example.stuffy.presentation.transaction.TransactionConfirmationViewModel
import com.example.stuffy.presentation.transaction.TransactionShareViewModel
import com.example.stuffy.presentation.transaction.TransactionTakeViewModel

import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val useCaseModule = module {
    factory<StuffyUseCase> { StuffyInteractor(get()) }
}

val viewModelModule = module {
    viewModel { ShareViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { TransactionConfirmationViewModel(get()) }
    viewModel { TransactionConfirmationDetailViewModel(get()) }
    viewModel { ConfirmationViewModel(get()) }
    viewModel { TransactionShareViewModel(get()) }
    viewModel { TransactionTakeViewModel(get()) }
}