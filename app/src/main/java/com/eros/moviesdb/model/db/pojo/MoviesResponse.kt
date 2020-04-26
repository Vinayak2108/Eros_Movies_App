package com.eros.moviesdb.model.db.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(val overview: String = "",
                 val originalLanguage: String = "",
                 val originalTitle: String = "",
                 val video: Boolean = false,
                 val title: String = "",
                 val posterPath: String = "",
                 val backdropPath: String = "",
                 val releaseDate: String = "",
                 val popularity: Double = 0.0,
                 val voteAverage: Int = 0,
                 @PrimaryKey(autoGenerate = false) val id: Int = 0,
                 val adult: Boolean = false,
                 val voteCount: Int = 0)


data class MoviesResponse(val page: Int = 0,
                          val totalPages: Int = 0,
                          val results: List<Movie>?,
                          val totalResults: Int = 0)


