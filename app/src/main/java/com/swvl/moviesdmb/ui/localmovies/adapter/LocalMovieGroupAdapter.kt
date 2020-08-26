package com.swvl.moviesdmb.ui.localmovies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.swvl.moviesdmb.R
import com.swvl.moviesdmb.databinding.ItemLocalMovieBinding
import com.swvl.moviesdmb.databinding.LocalMovieCardsBinding

class LocalMovieGroupAdapter(
    private val context: Context,
    private val movies: MutableList<LocalMovieItemViewModel>
) : RecyclerView.Adapter<LocalMovieGroupAdapter.LocalMovieViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): LocalMovieViewHolder =
        LocalMovieViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context),
                R.layout.item_local_movie,
                viewGroup,
                false
            )
        )

    override fun onBindViewHolder(viewHolder: LocalMovieViewHolder, position: Int) {
        viewHolder.itemLocalMovieBinding.item = movies[position]

        val animation = AnimationUtils.loadAnimation(
            context,
            R.anim.anim_bottom
        )

        viewHolder.itemView.startAnimation(animation)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movies: List<LocalMovieItemViewModel>) {
        this.movies.apply {
            clear()
            addAll(movies)
        }
    }

    inner class LocalMovieViewHolder(val itemLocalMovieBinding: ItemLocalMovieBinding) :
        RecyclerView.ViewHolder(itemLocalMovieBinding.root)
}