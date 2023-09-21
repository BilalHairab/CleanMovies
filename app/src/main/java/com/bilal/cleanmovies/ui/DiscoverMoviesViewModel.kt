package com.bilal.cleanmovies.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bilal.domain.entities.DataHolder
import com.bilal.domain.usecases.GetMoviesPageParams
import com.bilal.domain.usecases.GetMoviesPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Bilal Hairab on 15/09/2023.
 */
@HiltViewModel
class DiscoverMoviesViewModel @Inject constructor(private val moviesUseCase: GetMoviesPageUseCase) :
    ViewModel() {
    private val LOG_TAG = "DiscoverMoviesViewModel"
    private val _uiState = MutableStateFlow<DiscoverMoviesUiState>(DiscoverMoviesUiState.Idle)
    private var nextPage = 1
    val uiState: StateFlow<DiscoverMoviesUiState> = _uiState

    init {
        getMoviesPage()
    }

    fun getMoviesPage() {
        viewModelScope.launch {
            try {
                _uiState.emit(DiscoverMoviesUiState.LoadingState)
                val movies = moviesUseCase(GetMoviesPageParams(nextPage))
                nextPage += 1
                _uiState.emit(DiscoverMoviesUiState.MoviesAvailable((movies as DataHolder.Success).data))
            } catch (e: Exception) {
                _uiState.emit(DiscoverMoviesUiState.ErrorState(e.message!!))
                Log.e(LOG_TAG, "getMovies: ${e.message}");
            }
        }
    }
}