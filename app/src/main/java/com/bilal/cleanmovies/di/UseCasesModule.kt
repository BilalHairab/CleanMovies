package com.bilal.cleanmovies.di

import com.bilal.domain.repo.IMoviesRepo
import com.bilal.domain.usecases.GetMoviesPageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Bilal Hairab on 15/09/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun provideGetMoviesUseCase(moviesRepo: IMoviesRepo) = GetMoviesPageUseCase(moviesRepo)
}