package com.eros.moviesdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.eros.moviesdb.model.datasource.MovieDataSource
import com.eros.moviesdb.model.datasource.MovieDataSourceFactory
import com.eros.moviesdb.model.datasource.pageSize
import com.eros.moviesdb.model.db.pojo.Movie

class MoviesViewModel() : ViewModel() {

    var movies:LiveData<PagedList<Movie>>
    var dataSource:LiveData<PageKeyedDataSource<Int,Movie>>

    init {
        val dataSourceFactory = MovieDataSourceFactory()
        dataSource = dataSourceFactory.movieDataSource
        val config = PagedList.Config.Builder().setPageSize(pageSize).build()
        movies = LivePagedListBuilder(dataSourceFactory,config).build()
    }

}
