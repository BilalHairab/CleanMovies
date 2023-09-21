package com.bilal.data.datasources.remote

import com.bilal.domain.entities.DiscoverMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Bilal Hairab on 20/09/2023.
 */
interface MoviesAPIService {

    @GET("discover/movie")
    suspend fun getMoviesPage(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("sort_by") query: String? = null,
    ): DiscoverMoviesResponse

}