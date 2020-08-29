package com.swvl.moviesdmb.ui.localmovies

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mindorks.retrofit.coroutines.utils.Status
import com.swvl.moviesdmb.R
import com.swvl.moviesdmb.databinding.FragmentLocalMoviesBinding
import com.swvl.moviesdmb.ui.localmovies.adapter.LocalMovieGroupAdapter
import org.koin.android.ext.android.inject

class LocalMoviesFragment : Fragment() {
    private lateinit var adapter: LocalMovieGroupAdapter
    private val localMovieListViewModel: LocalMovieListViewModel by inject()
    private lateinit var viewDataBinding: FragmentLocalMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.main_menu, menu)
        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        return super.onCreateOptionsMenu(menu, inflater)
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