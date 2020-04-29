package com.eros.moviesdb.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eros.moviesdb.AdapterClickHandler
import com.eros.moviesdb.R
import com.eros.moviesdb.databinding.MovieItemBinding
import com.eros.moviesdb.model.db.pojo.Movie
import com.eros.moviesdb.utility.buildImageURL
import com.eros.moviesdb.utility.getYear

class MovieItemAdapter(private val clickHandler: AdapterClickHandler) : PagedListAdapter<Movie, MovieItemAdapter.ViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie:Movie? = getItem(position)
        movie?.let{
            val context = holder.binder.root.context
            holder.binder.name.text = it.title
            holder.binder.banner.setImageURI(buildImageURL(it.posterPath))
            holder.binder.rating.text = context.getString(R.string.rating,it.voteAverage.toString())
            holder.binder.launchYear.text = getYear(it.releaseDate)
            if(it.isFavourite){
                holder.binder.favIcon.setImageDrawable(context.getDrawable(R.drawable.ic_fav_true))
            }else{
                holder.binder.favIcon.setImageDrawable(context.getDrawable(R.drawable.ic_fav_false))
            }
            holder.binder.favIcon.setOnClickListener {
                clickHandler.setFavourite(movie,movie.isFavourite,position)
            }
            holder.binder.root.setOnClickListener{
                clickHandler.onItemClick(movie.id)
            }
        }
    }


    inner class ViewHolder(val binder : MovieItemBinding) : RecyclerView.ViewHolder(binder.root)

    private object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

}


