package com.devspacecinenow.detail.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.devspacecinenow.common.data.remote.RetrofitClient
import com.devspacecinenow.common.data.remote.model.MovieDto
import com.devspacecinenow.detail.data.DetailService
import com.devspacecinenow.detail.presentation.ui.MovieDetailUiData
import com.devspacecinenow.detail.presentation.ui.MovieDetailUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MovieDetailViewModel(
    private val detailService: DetailService
) : ViewModel() {

    private val _uiMovie = MutableStateFlow(MovieDetailUiState())
    val uiMovie: StateFlow<MovieDetailUiState> = _uiMovie

    fun fetchMovieDetail(movieId: String) {
        _uiMovie.value = MovieDetailUiState(isLoading = true)

        viewModelScope.launch {
            try {
                    val response = detailService.getMovieById(movieId)
                    if (response.isSuccessful) {
                        val movie = response.body()
                        if (movie != null) {
                            val movieUiData = convertMovieDto(movie)
                            _uiMovie.value = MovieDetailUiState(movie = movieUiData)
                        }
                    } else {
                        _uiMovie.value = MovieDetailUiState(isError = true)
                    }
            } catch (ex: Exception) {
                ex.printStackTrace()
                if (ex is UnknownHostException) {
                    _uiMovie.value = MovieDetailUiState(
                        isError = true,
                        errorMessage = "Not internet connection"
                    )
                } else {
                    _uiMovie.value = MovieDetailUiState(isError = true)
                }
            }
        }
    }

    fun cleanMovieId() {
        viewModelScope.launch {
            delay(1000)
            _uiMovie.value = MovieDetailUiState()
        }

    }

    private fun convertMovieDto(movieDto: MovieDto): MovieDetailUiData {
        val movieUiData = MovieDetailUiData(
            id = movieDto.id,
            title = movieDto.title,
            overview = movieDto.overview,
            image = movieDto.posterFullPath,
        )
        return movieUiData
    }

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