package com.devspacecinenow.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.devspacecinenow.common.data.RetrofitClient
import com.devspacecinenow.detail.data.DetailService

class MovieDetailViewModel(
    detailService: DetailService
) : ViewModel() {


    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

                val detailService =
                    RetrofitClient.retrofitInstance.create(DetailService::class.java)

                return MovieDetailViewModel(detailService) as T
            }
        }
    }
}