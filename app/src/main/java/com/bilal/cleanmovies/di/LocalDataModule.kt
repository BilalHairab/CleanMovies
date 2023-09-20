package com.bilal.cleanmovies.di

import android.app.Application
import androidx.room.Room
import com.bilal.data.datasources.local.MoviesDB
import com.bilal.data.datasources.remote.MoviesAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Bilal Hairab on 15/09/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    @Singleton
    fun provideRoomDataBase(application: Application) =
        Room.databaseBuilder(application, MoviesDB::class.java, "movies_db")
            .fallbackToDestructiveMigration().build().getMoviesDao()

}