package com.devspacecinenow.detail.presentation.ui

data class MovieDetailUiState(
    val movie : MovieDetailUiData? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage : String? = "Something went wrong"
)

data class MovieDetailUiData(
    val id: Int,
    val title: String,
    val overview: String,
    val image: String
)