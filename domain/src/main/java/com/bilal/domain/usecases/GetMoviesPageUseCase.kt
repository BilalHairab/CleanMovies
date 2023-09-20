package com.bilal.domain.usecases

import com.bilal.domain.entities.DataHolder
import com.bilal.domain.entities.Movie
import com.bilal.domain.entities.ResultError
import com.bilal.domain.repo.IMoviesRepo

/**
 * Created by Bilal Hairab on 20/09/2023.
 */

data class GetMoviesPageParams(val page: Int, val query: String)

class GetMoviesPageUseCase(private val moviesRepo: IMoviesRepo) :
    BaseUseCase<GetMoviesPageParams, List<Movie>>(moviesRepo) {
    override suspend fun invoke(params: GetMoviesPageParams): DataHolder<List<Movie>> {
        return try {
            DataHolder.Success(moviesRepo.getMoviesPage(params.page, params.query))
        } catch (e: Exception) {
            DataHolder.Fail(ResultError.UNKNOWN_ERROR_WITH_SERVER)
        }
    }
}