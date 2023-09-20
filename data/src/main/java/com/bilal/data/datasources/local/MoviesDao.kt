package com.bilal.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bilal.domain.entities.Movie

/**
 * Created by Bilal Hairab on 20/09/2023.
 */
@Dao
interface MoviesDao {
    @Insert
    suspend fun insertMovie(movie: Movie)

    @Insert
    suspend fun insertMoviesList(movies: List<Movie>)

    @Query("select * from movie LIMIT(20) OFFSET(:page * 20)")
    suspend fun getMoviesPage(page: Int): List<Movie>
}