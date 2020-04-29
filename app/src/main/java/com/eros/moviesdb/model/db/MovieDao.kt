package com.eros.moviesdb.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.eros.moviesdb.model.db.pojo.Movie

@Dao
interface MovieDao{

    @Insert
    fun addMovie(movie:Movie):Long

    @Delete
    fun deleteMovie(movie: Movie):Int

    @Query("SELECT * FROM movie")
    fun getAll():List<Movie>

    @Query("SELECT * FROM movie WHERE id = (:movieId)")
    fun getMovie(movieId:Int):Movie

}