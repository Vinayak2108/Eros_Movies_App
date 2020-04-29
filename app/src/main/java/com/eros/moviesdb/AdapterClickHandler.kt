package com.eros.moviesdb

import com.eros.moviesdb.model.db.pojo.Movie
import java.text.FieldPosition

interface AdapterClickHandler {
    fun onItemClick(id:Int)
    fun setFavourite(movie:Movie,favourite: Boolean,position: Int)
}