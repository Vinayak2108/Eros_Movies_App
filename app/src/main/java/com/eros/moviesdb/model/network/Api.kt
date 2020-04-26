package com.eros.moviesdb.model.network

import com.eros.moviesdb.model.pojo.MovieDetail
import com.eros.moviesdb.model.pojo.MoviesResponse
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val TOKEN = "1acb6020cf2e009f86eb99ecbe57fc9a"

interface API {

   @GET("discover/movie")
   fun getMovies(@Query("api_key") apiKey:String = TOKEN,
                 @Query("page") page:Int = 0): Call<MoviesResponse>


    @GET("/movie/{movie_id}")
    fun getMovieDetail(@Query("api_key") apiKey:String = TOKEN,
                  @Path("movie_id") movieId:Int = 0): Call<MovieDetail>

    @GET("/search/movie")
    fun searchMovie(@Query("api_key") apiKey:String = TOKEN,
                    @Query("query")query:String,
                    @Query("page") page:Int = 0):Call<MoviesResponse>

}

object APIProvider{
    val api : API by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        APICreator(API::class.java).create()
    }
}


class APICreator<out API>(private val clazz: Class<API>){
    internal fun create():API{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()))
            .build()
        return retrofit.create(clazz)
    }
}
