package com.bilal.cleanmovies.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bilal.domain.entities.DataHolder
import com.bilal.domain.entities.Movie
import com.bilal.domain.usecases.GetMoviesPageParams
import com.bilal.domain.usecases.GetMoviesPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Bilal Hairab on 15/09/2023.
 */
@HiltViewModel
class DiscoverMoviesViewModel @Inject constructor(
    @Named("movies_api_key") private val apiKey: String,
    private val moviesUseCase: GetMoviesPageUseCase
) :
    ViewModel() {
    private val LOG_TAG = "DiscoverMoviesViewModel"
    private val _uiState = MutableStateFlow<DiscoverMoviesUiState>(DiscoverMoviesUiState.Idle)
    private val loadedMovies = ArrayList<Movie>()
    private var nextPage = 1
    val uiState: StateFlow<DiscoverMoviesUiState> = _uiState

    init {
        getMoviesPage()
    }

    fun getMoviesPage() {
        viewModelScope.launch {
//            runBlocking {
                try {
                    _uiState.emit(DiscoverMoviesUiState.LoadingWithMoviesAvailable(loadedMovies, true))
                    val movies = moviesUseCase(GetMoviesPageParams(apiKey, nextPage))
                    loadedMovies.addAll((movies as DataHolder.Success).data)
                    nextPage += 1
                    _uiState.emit(DiscoverMoviesUiState.LoadingWithMoviesAvailable(loadedMovies, false))
                } catch (e: Exception) {
                    _uiState.emit(DiscoverMoviesUiState.ErrorState(e.message!!))
                    Log.e(LOG_TAG, "getMovies: ${e.message}");
                }
//            }
        }
    }
}