package com.eros.moviesdb.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eros.moviesdb.model.db.pojo.Movie

@Database(entities = [Movie::class],version = 1)
abstract class AppDatabase: RoomDatabase()
{
    abstract fun getMovieDao():MovieDao
}