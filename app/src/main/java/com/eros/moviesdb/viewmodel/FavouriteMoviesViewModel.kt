package com.eros.moviesdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.eros.moviesdb.model.datasource.MovieDataSourceFactory
import com.eros.moviesdb.model.datasource.pageSize
import com.eros.moviesdb.model.db.DBProvider
import com.eros.moviesdb.model.db.pojo.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteMoviesViewModel : ViewModel() {

    var movies: LiveData<PagedList<Movie>> = DBProvider.db.getMovieDao().getAll().toLiveData(pageSize)

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                DBProvider.db.getMovieDao().deleteMovie(movie)
            }
        }
    }

}
