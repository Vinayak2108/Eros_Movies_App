package com.eros.moviesdb.utility

fun buildImageURL(path: String?): String {
    return "https://image.tmdb.org/t/p/w500${path}"
}

fun getYear(date:String?): String {
    return if(!date.isNullOrBlank()){
        date.substring(0,4)
    }else{
        ""
    }
}