package com.example.stuffy.presentation.share

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.stuffy.core.domain.useCase.StuffyUseCase
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ShareViewModel(private val stuffyUseCase: StuffyUseCase) : ViewModel() {
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
    fun createProduct(files: MultipartBody.Part, description: RequestBody, name: RequestBody, location: RequestBody) =
        stuffyUseCase.createProduct(files,description,name,location).asLiveData()

}