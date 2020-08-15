package com.swvl.moviesdmb.ui.moviepaginglist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.swvl.moviesdmb.databinding.MoviePagingCardsBinding
import com.swvl.moviesdmb.models.Movie

/**
 * View Holder for a [Movie] RecyclerView list item.
 */
class PagingMovieViewHolder(val moviePagingCardsBinding: MoviePagingCardsBinding) :
    RecyclerView.ViewHolder(moviePagingCardsBinding.root)