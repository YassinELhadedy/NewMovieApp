package com.swvl.moviesdmb.ui.localmovies.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.swvl.moviesdmb.R
import com.swvl.moviesdmb.databinding.ItemLocalMovieBinding
import com.swvl.moviesdmb.ui.MainActivity
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

                val filterList = mutableListOf<LocalMovieItemViewModel>()
                if (constraint.toString().isEmpty()) {
                    filterList.addAll(cashMovies)
                } else {
                    if (constraint.toString().toIntOrNull() != null) {
                        filterList.addAll(cashMovies.filter { localMovie ->
                            localMovie.year.toString().contains(constraint.toString())
                        })
                    } else {
                        filterList.addAll(cashMovies.filter { localMovie ->
                            localMovie.title.toLowerCase(Locale.getDefault())
                                .contains(constraint.toString().toLowerCase(Locale.getDefault()))
                        })
                    }
                }

                val filterResult = FilterResults()
                filterResult.values = filterList

                return filterResult
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                movies.clear()
                movies.addAll(results?.values as Collection<LocalMovieItemViewModel>)
                notifyDataSetChanged()

                checkCountAndShowNumberOfMovies(
                    constraint.toString(),
                    (results.values as Collection<LocalMovieItemViewModel>).size.toString()
                )
            }
        }
    }

    private fun checkCountAndShowNumberOfMovies(textSearch: String, count: String) {
        if (textSearch.toIntOrNull() != null) {
            showSnackBar("Movie Year", count)
        } else {
            if (textSearch.isEmpty()) {
                showSnackBar("...", count)
            } else {
                showSnackBar("Movie Name", count)
            }

        }
    }

    private fun showSnackBar(searchType: String, message: String) {

        Snackbar.make(
            (context as MainActivity).findViewById(android.R.id.content),
            "Searching By ${searchType}.......-> $message Movies",
            Snackbar.LENGTH_INDEFINITE
        ).setTextColor(Color.RED).show()
    }
}