package com.bilal.domain.repo

import com.bilal.domain.entities.Movie

/**
 * Created by Bilal Hairab on 20/09/2023.
 */
interface IMoviesRepo: BaseRepository {
    suspend fun getMoviesPage(page: Int): List<Movie>
}