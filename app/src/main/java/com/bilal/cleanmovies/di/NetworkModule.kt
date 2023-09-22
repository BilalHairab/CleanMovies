package com.bilal.cleanmovies.di

import com.bilal.data.datasources.remote.MoviesAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Bilal Hairab on 15/09/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp() = OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS).build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) =
        Retrofit.Builder().client(client)
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Named("movies_api_key")
    fun provideMoviesApiKey() = "114fe6670282f6a632638661e5e86dee"

    @Provides
    @Named("movie_image_base_url")
    fun provideBaseUrlForMovies() = "https://image.tmdb.org/"

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(MoviesAPIService::class.java)
}