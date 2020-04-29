package com.eros.moviesdb.model.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.eros.moviesdb.model.db.DBProvider
import com.eros.moviesdb.model.db.pojo.Movie
import com.eros.moviesdb.model.db.pojo.MoviesResponse
import com.eros.moviesdb.model.network.APIProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val firstPage =  1
const val pageSize =  20

class MovieDataSource: PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        APIProvider.api.getMovies(page = firstPage).enqueue(object : Callback<MoviesResponse>{
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.i("API",t.message)
            }
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                response.body()?.results?.let {
                    callback.onResult(it,null,firstPage+1)
                }
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        APIProvider.api.getMovies(page = params.key).enqueue(object : Callback<MoviesResponse>{
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.i("API",t.message)
            }
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                response.body()?.let {
                    val nextPage:Int? = if(params.key<it.totalPages) params.key + 1
                    else null
                    it.results?.let {movies->
                        callback.onResult(movies,nextPage)
                    }
                }
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        APIProvider.api.getMovies(page = params.key).enqueue(object : Callback<MoviesResponse>{
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.i("API",t.message)
            }
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                response.body()?.results?.let {
                    val prePage:Int? = if(params.key>1 ) params.key - 1
                    else null
                    callback.onResult(it,prePage)
                }
            }
        })
    }
}

class MovieDataSourceFactory : DataSource.Factory<Int,Movie>(){
    private val _movieDataSource = MutableLiveData<PageKeyedDataSource<Int,Movie>>()
    val movieDataSource: LiveData<PageKeyedDataSource<Int, Movie>>
    get() = _movieDataSource

    override fun create(): DataSource<Int, Movie> {
        val dataSource = MovieDataSource()
        _movieDataSource.postValue(dataSource)
        return dataSource
    }
}