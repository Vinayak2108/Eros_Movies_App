package com.eros.moviesdb.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eros.moviesdb.databinding.MovieItemBinding
import com.eros.moviesdb.model.db.pojo.Movie
import com.eros.moviesdb.utility.buildImageURL

class MovieItemAdapter : PagedListAdapter<Movie, MovieItemAdapter.ViewHolder>(DiffCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie:Movie? = getItem(position)
        movie?.let{
            holder.binder.name.text = it.title
            holder.binder.banner.setImageURI(buildImageURL(it.posterPath))
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


