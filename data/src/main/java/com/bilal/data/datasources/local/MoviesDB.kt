package com.bilal.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bilal.domain.entities.Movie

/**
 * Created by Bilal Hairab on 20/09/2023.
 */
@Database(entities = [Movie::class], version = 1)
abstract class MoviesDB: RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao
}