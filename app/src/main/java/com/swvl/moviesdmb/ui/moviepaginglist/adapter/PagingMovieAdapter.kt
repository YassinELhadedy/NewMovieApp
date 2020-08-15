package com.swvl.moviesdmb.ui.moviepaginglist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.swvl.moviesdmb.R
import com.swvl.moviesdmb.models.Movie
import com.swvl.moviesdmb.ui.moviepaginglist.PopularMoreMovieListViewModel

/**
 * Adapter for the list of repositories.
 */
class PagingMovieAdapter(
    val context: Context, private val viewModel: PopularMoreMovieListViewModel
) : PagingDataAdapter<Movie, ViewHolder>(REPO_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        PagingMovieViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.movie_paging_cards,
                parent,
                false
            )
        )


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        (viewHolder as PagingMovieViewHolder).moviePagingCardsBinding.item = getItem(position)
        viewHolder.moviePagingCardsBinding.viewModel = viewModel

        val animation = AnimationUtils.loadAnimation(
            context,
            R.anim.anim_bottom
        )

        viewHolder.itemView.startAnimation(animation)
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}
