package com.eros.moviesdb.model.pojo

data class Movie(val overview: String = "",
                       val originalLanguage: String = "",
                       val originalTitle: String = "",
                       val video: Boolean = false,
                       val title: String = "",
                       val genreIds: List<Int>?,
                       val posterPath: String = "",
                       val backdropPath: String = "",
                       val releaseDate: String = "",
                       val popularity: Double = 0.0,
                       val voteAverage: Int = 0,
                       val id: Int = 0,
                       val adult: Boolean = false,
                       val voteCount: Int = 0)


data class MoviesResponse(val page: Int = 0,
                          val totalPages: Int = 0,
                          val results: List<Movie>?,
                          val totalResults: Int = 0)


