package com.bilal.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bilal.domain.entities.Movie

/**
 * Created by Bilal Hairab on 20/09/2023.
 */
@Database(entities = [Movie::class], version = 1)
@TypeConverters(MovieTypeConverters::class)
abstract class MoviesDB : RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao
}