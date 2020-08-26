package com.swvl.moviesdmb.ui.localmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.swvl.moviesdmb.R
import org.koin.android.ext.android.inject

class LocalMoviesFragment : Fragment() {
    private val localMovieListViewModel: LocalMovieListViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_local_movies, container, false)
    }
}