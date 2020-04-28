package com.eros.moviesdb.utility

fun buildImageURL(path: String): String {
    return "https://image.tmdb.org/t/p/w200${path}"
}