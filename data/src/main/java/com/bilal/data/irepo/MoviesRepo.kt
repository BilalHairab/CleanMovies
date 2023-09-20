package com.bilal.data.irepo

import com.bilal.data.datasources.local.MoviesDao
import com.bilal.data.datasources.remote.MoviesAPIService
import com.bilal.domain.entities.Movie
import com.bilal.domain.repo.IMoviesRepo

/**
 * Created by Bilal Hairab on 20/09/2023.
 */
class MoviesRepo(private val apiService: MoviesAPIService, private val moviesDao: MoviesDao) :
    IMoviesRepo {
    override suspend fun getMoviesPage(page: Int, query: String): List<Movie> {
        val serverMovies = getMoviesPageFromServer(page, query)
        if (serverMovies.isNotEmpty()) {
            saveMoviesPageInDB(serverMovies)
            return serverMovies
        }

        return getMoviesPageFromDB(page)
    }

    override suspend fun getMoviesPageFromServer(page: Int, query: String): List<Movie> {
        return try {
            apiService.getMoviesPage(page, query).results
        } catch (e: Exception) {
            e.printStackTrace();
            emptyList()
        }
    }

    override suspend fun saveMoviesPageInDB(movies: List<Movie>) {
        try {
            moviesDao.insertMoviesList(movies)
        } catch (e: Exception) {
            e.printStackTrace();
        }
    }

    override suspend fun getMoviesPageFromDB(page: Int): List<Movie> {
        return try {
            moviesDao.getMoviesPage(page)
        } catch (e: Exception) {
            e.printStackTrace();
            emptyList()
        }
    }
}