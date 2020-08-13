package com.swvl.moviesdmb.ui.movielist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.swvl.moviesdmb.R
import com.swvl.moviesdmb.databinding.MovieCardsBinding
import com.swvl.moviesdmb.ui.movielist.PopularMovieListViewModel
import com.swvl.moviesdmb.ui.movielist.adapter.MovieAdapter.MovieViewHolder

class MovieAdapter(
    private val context: Context,
    private val movies: MutableList<MovieItemViewModel>,
    private val viewModel: PopularMovieListViewModel
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): MovieViewHolder =
        MovieViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context),
                R.layout.movie_cards,
                viewGroup,
                false
            )
        )

    override fun onBindViewHolder(viewHolder: MovieViewHolder, position: Int) {
        viewHolder.movieCardsBinding.item = movies[position]
        viewHolder.movieCardsBinding.viewModel = viewModel

        val animation = AnimationUtils.loadAnimation(
            context,
            R.anim.anim_bottom
        )

        viewHolder.itemView.startAnimation(animation)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movies: List<MovieItemViewModel>) {
        this.movies.apply {
            clear()
            addAll(movies)
        }
    }

    inner class MovieViewHolder(val movieCardsBinding: MovieCardsBinding) :
        RecyclerView.ViewHolder(movieCardsBinding.root)
}