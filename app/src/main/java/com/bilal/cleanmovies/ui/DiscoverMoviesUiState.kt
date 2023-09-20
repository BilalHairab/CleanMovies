package com.bilal.cleanmovies.ui

import com.bilal.domain.entities.Movie

/**
 * Created by Bilal Hairab on 16/09/2023.
 */
sealed class DiscoverMoviesUiState {
    object Idle: DiscoverMoviesUiState()
    object LoadingState: DiscoverMoviesUiState()
    data class MoviesAvailable(val movies: List<Movie>): DiscoverMoviesUiState()
    data class ErrorState(val error: String): DiscoverMoviesUiState()

}