package com.eros.moviesdb.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.eros.moviesdb.AdapterClickHandler
import com.eros.moviesdb.R

import com.eros.moviesdb.databinding.MoviesFragmentBinding
import com.eros.moviesdb.model.db.pojo.Movie
import com.eros.moviesdb.view.adapters.MovieItemAdapter
import com.eros.moviesdb.viewmodel.MoviesViewModel

class MoviesFragment : BaseFragment(), AdapterClickHandler {

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
        val adapter = MovieItemAdapter(this)
        viewModel.movies.observe(viewLifecycleOwner,Observer {
            adapter.submitList(it)
        })
        viewModel.notifyDataChanges.observe(viewLifecycleOwner, Observer {
            if(it != -1) {
                viewBinder.list.adapter?.notifyDataSetChanged()
                viewModel.resetNotifyDataChanges()
            }
        })
        viewBinder.list.adapter = adapter
    }

    override fun onItemClick(id: Int) {
        val bundle = Bundle()
        bundle.putInt("ID",id)
        parentFragment?.findNavController()?.navigate(R.id.action_homeFragment_to_movieDetailFragment,bundle)
    }

    override fun setFavourite(movie:Movie, favourite: Boolean, position:Int) {
        movie.isFavourite = !favourite
        viewModel.setFavourite(movie,favourite,position)
    }

    fun submitQuery(query: String) {
        viewModel.setSearch(query)
    }


}
