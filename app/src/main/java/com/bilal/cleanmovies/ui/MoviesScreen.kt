package com.bilal.cleanmovies.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bilal.cleanmovies.ui.theme.CleanMoviesTheme
import com.bilal.domain.entities.Movie
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Bilal Hairab on 16/09/2023.
 */

@Composable
fun ShowDiscoveredMovies(viewModel: DiscoverMoviesViewModel) {
    CleanMoviesTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            val uiState = viewModel.uiState.collectAsState().value
            ConsumeState(uiState)
        }
    }
}

@Composable
fun ConsumeState(uiState: DiscoverMoviesUiState) {
    when (uiState) {
        is DiscoverMoviesUiState.Idle -> {
            IdleState()
        }

        is DiscoverMoviesUiState.LoadingState -> {
            LoadingState()
        }

        is DiscoverMoviesUiState.MoviesAvailable -> {
            MoviesList(uiState.movies)
        }

        is DiscoverMoviesUiState.ErrorState -> {
            ErrorState(uiState.error)
        }
    }
}

@Composable
fun IdleState() {
    OneTextState("Idle")
}

@Composable
fun LoadingState() {
    OneTextState("Loading Movies", false)
}

@Composable
fun OneTextState(text: String, isError: Boolean = false) {
    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .clip(shape = RoundedCornerShape(16.dp)),
    ) {
        Box(
            modifier = Modifier.size(350.dp), contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                color = if (isError) Color.Red else Color.White,
                fontSize = TextUnit(30F, TextUnitType.Sp)
            )
        }
    }
}

@Composable
fun ErrorState(error: String) {
    OneTextState("Error While Loading categories $error", true)
}

@Composable
fun MoviesList(list: List<Movie>) {
    LazyColumn {
        items(list) {
            MovieCard(it)
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable { },
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = movie.title,
                modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500" + movie.backdrop_path,
                contentDescription = "Translated description of what the image contains",
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = movie.overview,
                modifier = Modifier.padding(4.dp),
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MealzCleanTheme {
//        MealzList(
//            list = listOf(
//                Category("id1", "title1", "desc1", "thumb1"),
//                Category("id2", "title2", "desc2", "thumb2"),
//                Category("id3", "title3", "desc3", "thumb3"),
//            )
//        )
//    }
//}