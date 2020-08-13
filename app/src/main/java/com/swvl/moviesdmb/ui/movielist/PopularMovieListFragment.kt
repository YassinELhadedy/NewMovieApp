package com.swvl.moviesdmb.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mindorks.retrofit.coroutines.utils.Status
import com.swvl.moviesdmb.databinding.FragmentMovieListBinding
import com.swvl.moviesdmb.models.Movie
import com.swvl.moviesdmb.ui.movielist.adapter.MovieAdapter
import com.swvl.moviesdmb.ui.utils.EventObserver
import org.koin.android.ext.android.inject

class PopularMovieListFragment : Fragment() {
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
        setupNavigation()

        return viewDataBinding.root
    }

    private fun setupUI() {
        viewDataBinding.moviesList.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = MovieAdapter(requireContext(), mutableListOf(), popularMovieListViewModel)
        viewDataBinding.moviesList.adapter = adapter
    }

    private fun setupObservers() {
        popularMovieListViewModel.moviesItem.observe(requireActivity(), Observer {
            it?.let { resource ->
                when (resource.status) {//TODO : we should observe in VM instead of here.
                    Status.SUCCESS -> {
                        resource.data?.let { movies ->
                            popularMovieListViewModel.items = movies
                        }
                    }
                    Status.ERROR -> {
                    }
                    Status.LOADING -> {
                    }
                }
                viewDataBinding.viewmodel = popularMovieListViewModel
            }
        })
    }

    private fun setupNavigation() {
        popularMovieListViewModel.openMovieEvent.observe(viewLifecycleOwner, EventObserver {
            openMovieDetailFragment(it)
        })
    }

    private fun openMovieDetailFragment(movie: Movie) {
        val action =
            PopularMovieListFragmentDirections.actionPopularMovieListFragmentToMovieDetailFragment(
                movie
            )
        findNavController().navigate(action)
    }
}