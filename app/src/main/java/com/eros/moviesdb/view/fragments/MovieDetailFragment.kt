package com.eros.moviesdb.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eros.moviesdb.R

import com.eros.moviesdb.databinding.MovieDetailFragmentBinding
import com.eros.moviesdb.viewmodel.MovieDetailViewModel

class MovieDetailFragment : BaseFragment() {

    companion object {
        fun newInstance() =
            MovieDetailFragment()
    }

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var view: MovieDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = MovieDetailFragmentBinding.inflate(LayoutInflater.from(requireContext()))
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        arguments?.getInt("ID")?.let { viewModel.loadData(it) }
        renderUI()
    }

    override fun renderUI() {
        viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            view.banner.setImageURI(viewModel.getImagePath(it.posterPath))
            view.title.text = it.title
            view.yearLengthCertificate.text = viewModel.getYearCountryCrtString(it.releaseDate,it.adult,it.runtime)
            view.rating.text = getString(R.string.rating,it.voteAverage.toString())
            view.description.text = it.overview
            view.language.text = viewModel.getLanguageString(it.spokenLanguages)
            view.tagLine.text = it.tagline

            it.genres?.let {genres->
                for (genre in genres){
                    if(!genre.name.isNullOrBlank())
                        view.genre.addView(getFlowChild(genre.name))
                }
            }


        })
    }

    private fun getFlowChild(text:String?):View{
        val flowChild = LayoutInflater.from(requireContext()).inflate(R.layout.flow_item,null,false)
        flowChild.findViewById<TextView>(R.id.name).text = text
        return flowChild
    }

}
