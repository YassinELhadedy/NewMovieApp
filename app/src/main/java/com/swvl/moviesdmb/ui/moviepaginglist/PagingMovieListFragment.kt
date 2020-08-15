package com.swvl.moviesdmb.ui.moviepaginglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.swvl.moviesdmb.R
import com.swvl.moviesdmb.ui.movielist.PopularMovieListViewModel
import com.swvl.moviesdmb.ui.moviepaginglist.adapter.PagingMovieAdapter
import kotlinx.android.synthetic.main.fragment_paging_movie_list.view.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class PagingMovieListFragment : Fragment() {

    private val popularMovieListViewModel: PopularMovieListViewModel by inject()
    private val popularMoreMovieListViewModel: PopularMoreMovieListViewModel by inject()
    private lateinit var adapter: PagingMovieAdapter
    lateinit var rootView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_paging_movie_list, container, false)

        initAdapter()
        search()
        return rootView
    }

    private fun initAdapter() {
        adapter = PagingMovieAdapter(requireContext(), popularMovieListViewModel)
        rootView.list.layoutManager = GridLayoutManager(requireContext(), 2)
        rootView.list.adapter = adapter
    }

    private fun search() {
        // Make sure we cancel the previous job before creating a new one
        lifecycleScope.launch {
            popularMoreMovieListViewModel.getPopularMovieList().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}