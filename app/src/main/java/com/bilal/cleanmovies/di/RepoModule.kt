package com.bilal.cleanmovies.di

import com.bilal.data.datasources.local.MoviesDao
import com.bilal.data.datasources.remote.MoviesAPIService
import com.bilal.data.irepo.MoviesRepo
import com.bilal.domain.repo.IMoviesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Bilal Hairab on 15/09/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideMoviesRepo(apiService: MoviesAPIService, moviesDao: MoviesDao): IMoviesRepo {
        return MoviesRepo(apiService, moviesDao)
    }
}