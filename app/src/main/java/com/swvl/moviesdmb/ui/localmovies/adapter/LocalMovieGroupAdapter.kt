package com.swvl.moviesdmb.ui.localmovies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.swvl.moviesdmb.R
import com.swvl.moviesdmb.databinding.ItemLocalMovieBinding

class LocalMovieGroupAdapter(
    private val context: Context,
    private val movies: MutableList<LocalMovieItemViewModel>
) : RecyclerView.Adapter<LocalMovieGroupAdapter.LocalMovieViewHolder>(), Filterable {

    val moviesTilte = mutableListOf<String>()

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
            moviesTilte.addAll(movies.map { it.title })
        }
    }

    inner class LocalMovieViewHolder(val itemLocalMovieBinding: ItemLocalMovieBinding) :
        RecyclerView.ViewHolder(itemLocalMovieBinding.root)

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val filterList = mutableListOf<String>()
                if (constraint.toString().isEmpty()) {
                    filterList.addAll(moviesTilte)
                } else {
                    for (movie in moviesTilte) {
                        if (movie.toLowerCase()
                                .contains(constraint.toString().toLowerCase())
                        ) {
                            filterList.add(movie)
                        }
                    }
                }

                val filterResult = FilterResults()
                filterResult.values = filterList

                return filterResult
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                moviesTilte.clear()
                moviesTilte.addAll(results?.values as Collection<String>)
                notifyDataSetChanged()
            }
        }
    }
}