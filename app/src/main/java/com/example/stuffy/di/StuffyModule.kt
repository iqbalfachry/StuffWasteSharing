package com.example.stuffy.di

import com.example.stuffy.core.domain.useCase.StuffyInteractor
import com.example.stuffy.core.domain.useCase.StuffyUseCase

import com.example.stuffy.presentation.share.ShareViewModel

import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val useCaseModule = module {
    factory<StuffyUseCase> { StuffyInteractor(get()) }
}

val viewModelModule = module {
    viewModel { ShareViewModel(get()) }

}