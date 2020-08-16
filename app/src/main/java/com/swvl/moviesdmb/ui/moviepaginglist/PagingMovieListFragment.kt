package com.swvl.moviesdmb.ui.moviepaginglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.swvl.moviesdmb.databinding.FragmentPagingMovieListBinding
import com.swvl.moviesdmb.models.Movie
import com.swvl.moviesdmb.ui.moviepaginglist.adapter.PagingMovieAdapter
import com.swvl.moviesdmb.ui.utils.EventObserver
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class PagingMovieListFragment : Fragment() {
    private val popularMoreMovieListViewModel: PopularMoreMovieListViewModel by inject()
    private lateinit var adapter: PagingMovieAdapter
    private lateinit var viewDataBinding: FragmentPagingMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewDataBinding = FragmentPagingMovieListBinding.inflate(inflater, container, false)

        initAdapter()
        search()
        setupNavigation()

        return viewDataBinding.root
    }

    private fun initAdapter() {
        adapter = PagingMovieAdapter(requireContext(), popularMoreMovieListViewModel)
        viewDataBinding.moviesList.layoutManager = GridLayoutManager(requireContext(), 2)
        viewDataBinding.moviesList.adapter = adapter
    }

    private fun search() {
        // Make sure we cancel the previous job before creating a new one
        lifecycleScope.launch {
            popularMoreMovieListViewModel.getPopularMovieList().collectLatest {
                popularMoreMovieListViewModel.items = it
                viewDataBinding.viewmodel = popularMoreMovieListViewModel
                popularMoreMovieListViewModel._dataLoading.value = false
            }
        }
    }

    private fun setupNavigation() {
        popularMoreMovieListViewModel.openMovieEvent.observe(viewLifecycleOwner, EventObserver {
            openMovieDetailFragment(it)
        })
    }

    private fun openMovieDetailFragment(movie: Movie) {
        val action =
            PagingMovieListFragmentDirections.actionPagingMovieListFragmentToMovieDetailFragment(
                movie
            )
        findNavController().navigate(action)
    }
}