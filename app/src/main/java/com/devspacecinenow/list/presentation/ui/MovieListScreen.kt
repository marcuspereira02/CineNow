package com.devspacecinenow.list.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.devspacecinenow.list.presentation.MovieListViewModel

@Composable
fun MovieListScreen(
    navController: NavHostController,
    viewModel: MovieListViewModel
) {
    val topRatedMovies by viewModel.uiTopRatedMovies.collectAsState()
    val nowPlayingMovies by viewModel.uiNowPlaying.collectAsState()
    val popularMovies by viewModel.uiPopularMovies.collectAsState()
    val upcomingMovies by viewModel.uiUpcomingMovies.collectAsState()

    MovieListContent(
        topRatedMovies = topRatedMovies,
        nowPlayingMovies = nowPlayingMovies,
        popularMovies = popularMovies,
        upcomingMovies = upcomingMovies
    ) { itemClicked ->
        navController.navigate(route = "movieDetail/${itemClicked.id}")
    }
}

@Composable
private fun MovieListContent(
    topRatedMovies: MovieListUiState,
    nowPlayingMovies: MovieListUiState,
    popularMovies: MovieListUiState,
    upcomingMovies: MovieListUiState,
    onClick: (MovieUiData) -> Unit

) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "CineNow",
            fontSize = 34.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
        )

        Divider(
            color = Color.LightGray.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        )

        MovieSession(
            label = "⭐ Top rated",
            topRatedMovies,
            onClick = onClick
        )

        MovieSession(
            label = "\uD83C\uDFAC Now playing",
            nowPlayingMovies,
            onClick = onClick
        )

        MovieSession(
            label = "\uD83D\uDD25 Popular",
            popularMovies,
            onClick = onClick
        )

        MovieSession(
            label = "⏳ Upcoming",
            upcomingMovies,
            onClick = onClick
        )
    }
}

@Composable
private fun MovieSession(
    label: String,
    movieListUiState: MovieListUiState,
    onClick: (MovieUiData) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 8.dp, start = 8.dp)
    ) {

        Text(
            fontSize = 22.sp, text = label, fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.size(8.dp))

        if (movieListUiState.isLoading) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Loading...",
                fontSize = 16.sp
            )
            return

        } else if (movieListUiState.isError) {
            Text(
                color = Color.Red,
                text = movieListUiState.errorMessage ?: "",
                fontWeight = FontWeight.SemiBold
            )

        } else {
            MovieList(movieList = movieListUiState.list, onClick = onClick)
        }
        Divider(
            color = Color.LightGray.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}


@Composable
private fun MovieList(
    movieList: List<MovieUiData>, onClick: (MovieUiData) -> Unit
) {

    LazyRow {
        items(movieList) {
            MovieItem(
                movieUiData = it, onClick = onClick
            )
        }

    }
}

@Composable
private fun MovieItem(
    movieUiData: MovieUiData, onClick: (MovieUiData) -> Unit
) {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .clickable {
                onClick.invoke(movieUiData)
            }) {
        AsyncImage(
            modifier = Modifier
                .padding(end = 4.dp)
                .width(120.dp)
                .height(150.dp),
            contentScale = ContentScale.Crop,
            model = movieUiData.image,
            contentDescription = "${movieUiData.title} Poster image"
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold,
            text = movieUiData.title
        )
        Text(
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = movieUiData.overview

        )
    }
}



