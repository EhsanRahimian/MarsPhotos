package com.nicootech.marsapi.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicootech.marsapi.network.MarsApi
import kotlinx.coroutines.launch
import java.lang.Exception

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()

    val status: LiveData<String> = _status

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getPhotos()
                _status.value = "Success: ${listResult.size} Mars photo retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }

        }
    }
}