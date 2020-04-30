package com.eros.moviesdb.viewmodel

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.eros.moviesdb.model.datasource.MovieDataSourceFactory
import com.eros.moviesdb.model.datasource.pageSize
import com.eros.moviesdb.model.db.DBProvider
import com.eros.moviesdb.model.db.pojo.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.ParsePosition

class MoviesViewModel() : ViewModel() {

    var movies:LiveData<PagedList<Movie>>
    lateinit var dataSource:LiveData<PageKeyedDataSource<Int,Movie>>
    private val _notifyDataChanges = MutableLiveData<Int>()
    val notifyDataChanges:LiveData<Int>
    get() = _notifyDataChanges

    private val _searchQuery = MutableLiveData<String>("")
    private val searchQuery:LiveData<String>
    get() = _searchQuery

    init {
        movies = Transformations.switchMap(searchQuery) {
            val dataSourceFactory = MovieDataSourceFactory(it)
            dataSource = dataSourceFactory.movieDataSource
            val config = PagedList.Config.Builder().setPageSize(pageSize).build()
            return@switchMap LivePagedListBuilder(dataSourceFactory,config).build()
        }
    }


    fun setFavourite(movie: Movie, favourite: Boolean,position: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val status = if(favourite){
                    DBProvider.db.getMovieDao().deleteMovie(movie).toLong()
                }else{
                    DBProvider.db.getMovieDao().addMovie(movie)
                }
                if(status != 0L){
                    _notifyDataChanges.postValue(position)
                }
            }
        }

    }

    fun resetNotifyDataChanges() {
        _notifyDataChanges.value = -1
    }

    fun setSearch(query: String) {
        _searchQuery.value = query
    }
}
