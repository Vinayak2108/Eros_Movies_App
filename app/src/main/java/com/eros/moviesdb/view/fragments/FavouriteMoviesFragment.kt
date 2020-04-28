package com.eros.moviesdb.view.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.eros.moviesdb.R
import com.eros.moviesdb.viewmodel.FavouriteMoviesViewModel

class FavouriteMoviesFragment : BaseFragment() {

    companion object {
        fun newInstance() =
            FavouriteMoviesFragment()
    }

    private lateinit var viewModel: FavouriteMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favourite_movies_fragment, container, false)
    }

    override fun renderUI() {
        TODO("Not yet implemented")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FavouriteMoviesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
