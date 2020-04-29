package com.eros.moviesdb.model.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eros.moviesdb.app.Eros
import com.eros.moviesdb.model.db.pojo.Movie

private const val DB_NAME = "movies"

@Database(entities = [Movie::class],version = 1)
abstract class AppDatabase: RoomDatabase()
{
    abstract fun getMovieDao():MovieDao
}

object DBProvider{
    val db by lazy {
        Room.databaseBuilder(Eros.appContext,AppDatabase::class.java,DB_NAME).build()
    }
}