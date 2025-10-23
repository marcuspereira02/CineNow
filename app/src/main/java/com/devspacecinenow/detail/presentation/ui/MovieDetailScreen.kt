package com.devspacecinenow.detail.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.devspacecinenow.detail.presentation.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    id: String,
    navHostController: NavHostController,
    viewModel: MovieDetailViewModel
) {
    val movieUiState by viewModel.uiMovie.collectAsState()

    LaunchedEffect(id) {
        viewModel.fetchMovieDetail(id)
    }

    when {
        movieUiState.isLoading -> {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Loading...",
                fontSize = 16.sp
            )
        }

        movieUiState.isError -> {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 48.dp),
                textAlign = TextAlign.Center,
                color = Color.Red,
                text = movieUiState.errorMessage ?: "",
                fontWeight = FontWeight.SemiBold
            )
            MovieDetailHeader(
                navHostController = navHostController,
                viewModel = viewModel,
                title = "ERROR!"
            )
        }

        movieUiState.movie != null -> {
            val movie = movieUiState.movie
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                MovieDetailHeader(
                    navHostController = navHostController,
                    viewModel = viewModel,
                    title = movie!!.title
                )
                MovieDetailContent(movie)
            }
        }
    }
}

@Composable
private fun MovieDetailContent(
    movie: MovieDetailUiData?
) {

    Column(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
                .padding(start = 8.dp, end = 8.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
            model = movie?.image,
            contentDescription = "${movie?.title} Poster image"
        )

        Text(
            modifier = Modifier.padding(16.dp),
            text = movie!!.overview,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun MovieDetailHeader(
    navHostController: NavHostController,
    viewModel: MovieDetailViewModel,
    title: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                viewModel.cleanMovieId()
                navHostController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    "Back Button"
                )
            }
            Text(
                modifier = Modifier
                    .padding(start = 4.dp),
                text = title,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}