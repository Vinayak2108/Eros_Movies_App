package com.eros.moviesdb.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavigatorProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.eros.moviesdb.AdapterClickHandler

import com.eros.moviesdb.databinding.FavouriteMoviesFragmentBinding
import com.eros.moviesdb.model.db.pojo.Movie
import com.eros.moviesdb.view.adapters.MovieItemAdapter
import com.eros.moviesdb.viewmodel.FavouriteMoviesViewModel

class FavouriteMoviesFragment : BaseFragment(), AdapterClickHandler {

    companion object {
        fun newInstance() =
            FavouriteMoviesFragment()
    }

    private lateinit var viewModel: FavouriteMoviesViewModel
    private lateinit var viewBinding: FavouriteMoviesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FavouriteMoviesFragmentBinding.inflate(LayoutInflater.from(requireContext()))
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavouriteMoviesViewModel::class.java)
        renderUI()
    }

    override fun renderUI() {
        viewBinding.list.layoutManager = GridLayoutManager(requireContext(),2)
        viewBinding.list.setHasFixedSize(true)
        val adapter = MovieItemAdapter(this)
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewBinding.list.adapter = adapter
    }

    override fun onItemClick(id: Int) {

    }

    override fun setFavourite(movie: Movie, favourite: Boolean, position: Int) {
        viewModel.deleteMovie(movie)
    }


}
