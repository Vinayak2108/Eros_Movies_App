package com.eros.moviesdb.viewmodel

import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eros.moviesdb.R
import com.eros.moviesdb.model.db.DBProvider
import com.eros.moviesdb.model.db.pojo.GenresItem
import com.eros.moviesdb.model.db.pojo.MovieDetail
import com.eros.moviesdb.model.db.pojo.SpokenLanguagesItem
import com.eros.moviesdb.model.network.APIProvider
import com.eros.moviesdb.utility.buildImageURL
import com.eros.moviesdb.utility.getHrMin
import com.eros.moviesdb.utility.getYear
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

class MovieDetailViewModel : ViewModel() {


    fun getImagePath(posterPath: String): String {
        return buildImageURL(posterPath)
    }

    fun getYearCountryCrtString(
        releaseDate: String,
        adult: Boolean,
        runTime: Int?
    ): CharSequence? {
        val builder = StringBuilder()
        builder.append(getYear(releaseDate))
        if(runTime != null && 0 != runTime) {
            builder.append(" • ")
            builder.append(getHrMin(runTime))
        }
        if(adult){
            builder.append(" • ")
            builder.append("18+")
        }
        return builder.toString()
    }

    fun loadData(id:Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = APIProvider.api.getMovieDetail(movieId = id).execute()
                if(response.isSuccessful){
                    _movieDetails.postValue(response.body())
                }
            }
        }
    }

    fun getLanguageString(spokenLanguages: List<SpokenLanguagesItem>?): CharSequence? {
        val builder = StringBuilder()
        spokenLanguages?.let {
            for (language in spokenLanguages){
                builder.append(language.name)
                if(language != spokenLanguages.last())
                    builder.append("•")
            }
        }
        return builder.toString()
    }


    private val _movieDetails = MutableLiveData<MovieDetail>()
    val movieDetails: LiveData<MovieDetail>
        get() = _movieDetails


}
