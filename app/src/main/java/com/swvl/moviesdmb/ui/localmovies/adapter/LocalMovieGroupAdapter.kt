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
import java.util.*

class LocalMovieGroupAdapter(
    private val context: Context,
    private val movies: MutableList<LocalMovieItemViewModel>
) : RecyclerView.Adapter<LocalMovieGroupAdapter.LocalMovieViewHolder>(), Filterable {

    val cashMovies: List<LocalMovieItemViewModel> by lazy {
        movies.toList()
    }

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
            cashMovies
        }
    }

    class LocalMovieViewHolder(val itemLocalMovieBinding: ItemLocalMovieBinding) :
        RecyclerView.ViewHolder(itemLocalMovieBinding.root)

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val filterList = mutableListOf<String>()
                if (constraint.toString().isEmpty()) {
                    filterList.addAll(movies.map { it.title })
                } else {
                    for (movie in cashMovies) {
                        if (movie.title.toLowerCase(Locale.getDefault())
                                .contains(constraint.toString().toLowerCase(Locale.getDefault()))
                        ) {
                            filterList.add(movie.title)
                        }
                    }
                }

                val filterResult = FilterResults()
                filterResult.values = filterList

                return filterResult
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                val listAfterFilter =
                    filterFromList(cashMovies, (results?.values as Collection<String>))
                movies.clear()
                if (constraint.toString().isEmpty()) {
                    movies.addAll(cashMovies)
                } else {
                    movies.addAll(listAfterFilter)
                }
                notifyDataSetChanged()
            }
        }
    }

    private fun filterFromList(
        list: List<LocalMovieItemViewModel>,
        results: Collection<String>
    ): List<LocalMovieItemViewModel> {
        return list.filter {
                    results.contains(
                        it.title
                    )
        }
    }
}