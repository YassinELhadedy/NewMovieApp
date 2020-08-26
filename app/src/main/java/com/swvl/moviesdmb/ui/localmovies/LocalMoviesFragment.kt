package com.swvl.moviesdmb.ui.localmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mindorks.retrofit.coroutines.utils.Status
import com.swvl.moviesdmb.databinding.FragmentLocalMoviesBinding
import com.swvl.moviesdmb.ui.localmovies.adapter.LocalMovieGroupAdapter
import org.koin.android.ext.android.inject

class LocalMoviesFragment : Fragment() {
    private lateinit var adapter: LocalMovieGroupAdapter
    private val localMovieListViewModel: LocalMovieListViewModel by inject()
    private lateinit var viewDataBinding: FragmentLocalMoviesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewDataBinding = FragmentLocalMoviesBinding.inflate(inflater, container, false)
        initAdapter()
        setupObservers()

        return viewDataBinding.root
    }

    private fun initAdapter() {
        adapter = LocalMovieGroupAdapter(requireContext(), mutableListOf())
        viewDataBinding.rvMoviesFragments.layoutManager = GridLayoutManager(requireContext(), 1)
        viewDataBinding.rvMoviesFragments.adapter = adapter
    }

    private fun setupObservers() {
        localMovieListViewModel.moviesItem.observe(requireActivity(), Observer {
            it?.let { resource ->
                when (resource.status) {//TODO : we should observe in VM instead of here.
                    Status.SUCCESS -> {
                        resource.data?.let { movies ->
                            localMovieListViewModel.items = movies
                        }
                    }
                    Status.ERROR -> {
                    }
                    Status.LOADING -> {
                    }
                }
                viewDataBinding.viewmodel = localMovieListViewModel
            }
        })
    }

}