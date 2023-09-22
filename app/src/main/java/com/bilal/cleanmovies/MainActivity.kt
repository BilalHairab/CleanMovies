package com.bilal.cleanmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.bilal.cleanmovies.ui.DiscoverMoviesViewModel
import com.bilal.cleanmovies.ui.ShowDiscoveredMovies
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: DiscoverMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContent {
                ShowDiscoveredMovies(viewModel)
            }
        }
    }
}