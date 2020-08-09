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
import com.swvl.moviesdmb.databinding.FragmentMovieListBinding
import com.swvl.moviesdmb.models.Movie
import com.swvl.moviesdmb.ui.movielist.adapter.MovieAdapter
import com.swvl.moviesdmb.ui.utils.Resource
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.koin.android.ext.android.inject

class PopularMovieListFragment : Fragment(), MovieAdapter.OnMovieClickListener {
    private lateinit var adapter: MovieAdapter
    private val popularMovieListViewModel: PopularMovieListViewModel by inject()
    private lateinit var viewDataBinding: FragmentMovieListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewDataBinding = FragmentMovieListBinding.inflate(inflater, container, false)

        setupUI()
        setupObservers()

        return viewDataBinding.root
    }

    private fun setupUI() {
        viewDataBinding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = MovieAdapter(requireContext(), mutableListOf(), this)
        viewDataBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                viewDataBinding.recyclerView.context,
                (viewDataBinding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        viewDataBinding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        popularMovieListViewModel.moviesItem.observe(requireActivity(), Observer {
            it?.let { resource ->
                when (resource.status) {//TODO : we should observe in VM instead of here.
                    Status.SUCCESS -> {
                        resource.data?.let { movies ->
                            popularMovieListViewModel.items = Resource(Status.SUCCESS, movies, null)
                        }
                    }
                    Status.ERROR -> {
                        popularMovieListViewModel.items = Resource(Status.ERROR, null, null)

                    }
                    Status.LOADING -> {
                        popularMovieListViewModel.items = Resource(Status.LOADING, null, null)

                    }
                }
                viewDataBinding.viewmodel = popularMovieListViewModel
            }
        })
    }

    override fun onClickMovie(movie: Movie) {
        openMovieDetailFragment(movie)
    }

    private fun openMovieDetailFragment(movie: Movie) {
        val action =
            PopularMovieListFragmentDirections.actionPopularMovieListFragmentToMovieDetailFragment(
                movie
            )
        findNavController().navigate(action)
    }
}