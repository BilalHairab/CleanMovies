package com.bilal.cleanmovies.ui

import com.bilal.domain.entities.Movie

/**
 * Created by Bilal Hairab on 16/09/2023.
 */
sealed class DiscoverMoviesUiState {
    object Idle: DiscoverMoviesUiState()
    data class LoadingWithMoviesAvailable(val movies: List<Movie>, val loading: Boolean): DiscoverMoviesUiState()
    data class ErrorState(val error: String): DiscoverMoviesUiState()

}