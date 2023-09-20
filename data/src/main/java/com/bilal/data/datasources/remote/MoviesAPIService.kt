package com.bilal.data.datasources.remote

import com.bilal.domain.entities.DiscoverMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Bilal Hairab on 20/09/2023.
 */
interface MoviesAPIService {

    @GET("discover/movie?api_key=114fe6670282f6a632638661e5e86dee")
    suspend fun getMoviesPage(
        @Query("page") page: Int,
        @Query("sort_by") query: String,
    ): DiscoverMoviesResponse

}