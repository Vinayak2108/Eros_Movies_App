package com.eros.moviesdb.model.db.pojo

data class SpokenLanguagesItem(val name: String = "",
                               val iso: String = "")


data class GenresItem(val name: String = "",
                      val id: Int = 0)


data class ProductionCountriesItem(val iso: String = "",
                                   val name: String = "")


data class ProductionCompaniesItem(val logoPath: String? = null,
                                   val name: String = "",
                                   val id: Int = 0,
                                   val originCountry: String = "")


data class MovieDetail(val originalLanguage: String = "",
                       val imdbId: String = "",
                       val video: Boolean = false,
                       val title: String = "",
                       val backdropPath: String = "",
                       val revenue: Int = 0,
                       val genres: List<GenresItem>?,
                       val popularity: Double = 0.0,
                       val productionCountries: List<ProductionCountriesItem>?,
                       val id: Int = 0,
                       val voteCount: Int = 0,
                       val budget: Int = 0,
                       val overview: String = "",
                       val originalTitle: String = "",
                       val runtime: Int = 0,
                       val posterPath: String = "",
                       val spokenLanguages: List<SpokenLanguagesItem>?,
                       val productionCompanies: List<ProductionCompaniesItem>?,
                       val releaseDate: String = "",
                       val voteAverage: Double = 0.0,
                       val tagline: String = "",
                       val adult: Boolean = false,
                       val homepage: String = "",
                       val status: String = "")


