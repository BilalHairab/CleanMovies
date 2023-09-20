package com.bilal.domain.repo

import com.bilal.domain.entities.Movie

/**
 * Created by Bilal Hairab on 20/09/2023.
 */
interface IMoviesRepo: BaseRepository {
    suspend fun getMoviesPage(page: Int, query: String): List<Movie>

    suspend fun getMoviesPageFromServer(page: Int, query: String): List<Movie>

    suspend fun saveMoviesPageInDB(movies: List<Movie>)

    suspend fun getMoviesPageFromDB(page: Int): List<Movie>
    }