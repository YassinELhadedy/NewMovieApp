package com.swvl.moviesdmb.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.retrofit.coroutines.utils.Status
import com.swvl.moviesdmb.R
import com.swvl.moviesdmb.models.Movie
import com.swvl.moviesdmb.ui.movielist.adapter.MovieAdapter
import com.swvl.moviesdmb.ui.movielist.adapter.MovieItemViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.fragment_movie_list.view.*
import org.koin.android.ext.android.inject

class PopularMovieListFragment : Fragment(), MovieAdapter.OnMovieClickListener {
    private lateinit var rootView: View
    private lateinit var adapter: MovieAdapter
    private val popularMovieListViewModel: PopularMovieListViewModel by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_movie_list, container, false)

        setupUI()
        setupObservers()

        return rootView
    }

    private fun setupUI() {
        rootView.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        adapter = MovieAdapter(requireContext(), mutableListOf(), this)
        rootView.recyclerView.addItemDecoration(
            DividerItemDecoration(
                rootView.recyclerView.context,
                (rootView.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        rootView.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        popularMovieListViewModel.getPopularMovieList().observe(requireActivity(), Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { movies -> retrieveList(movies) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(movies: List<MovieItemViewModel>) {
        adapter.apply {
            addMovies(movies)
            notifyDataSetChanged()
        }
    }

    override fun onClickMovie(movie: Movie) {
        openMovieDetailFragment(movie)
    }

    private fun openMovieDetailFragment(movie: Movie) {

        val action =
            PopularMovieListFragmentDirections.actionPopularMovieListFragmentToMovieDetailFragment(movie)
        findNavController().navigate(action)
    }
}