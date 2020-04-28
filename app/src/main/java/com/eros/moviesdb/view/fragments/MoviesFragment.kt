package com.eros.moviesdb.view.fragments

import android.os.Binder
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import com.eros.moviesdb.R
import com.eros.moviesdb.databinding.MoviesFragmentBinding
import com.eros.moviesdb.view.adapters.MovieItemAdapter
import com.eros.moviesdb.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.movies_fragment.*

class MoviesFragment : BaseFragment() {

    companion object {
        fun newInstance() = MoviesFragment()
    }

    private lateinit var viewModel: MoviesViewModel
    private lateinit var viewBinder: MoviesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinder = MoviesFragmentBinding.inflate(inflater)
        return viewBinder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        renderUI()
    }

    override fun renderUI() {
        viewBinder.list.layoutManager = GridLayoutManager(requireContext(),2)
        viewBinder.list.setHasFixedSize(true)
        val adapter = MovieItemAdapter()
        viewModel.movies.observe(viewLifecycleOwner,Observer {
            adapter.submitList(it)
        })
        list.adapter = adapter
    }


}
