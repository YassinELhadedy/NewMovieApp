package com.swvl.moviesdmb.ui.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.swvl.moviesdmb.R
import com.swvl.moviesdmb.databinding.TrailerCardBinding
import com.swvl.moviesdmb.models.Trailer
import com.swvl.moviesdmb.ui.moviedetail.MovieDetailFragment.Companion.YOUTUBE_VIDEO_URL

class TrailerAdapter(
    private val trailerList: MutableList<Trailer>, private val mListener: OnYouTubeLinkClickListener
) : RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrailerViewHolder =
        TrailerViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.trailer_card,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = trailerList.size

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.trailerCardsBinding.item = trailerList[position]
        holder.trailerCardsBinding.root.setOnClickListener {
            mListener.onClickYouTubeTrailerMovie(getUrl(trailerList[position]))
        }
    }

    fun addTrailers(trailers: List<Trailer>) {
        this.trailerList.apply {
            clear()
            addAll(trailers)
        }
    }

    inner class TrailerViewHolder(val trailerCardsBinding: TrailerCardBinding) : RecyclerView.ViewHolder(trailerCardsBinding.root)

    private fun getUrl(trailer: Trailer): String {
        return if (SITE_YOUTUBE.equals(trailer.site, ignoreCase = true)) {
            String.format(YOUTUBE_VIDEO_URL, trailer.videoId)
        } else {
            EMPTY
        }
    }

    companion object {
        const val SITE_YOUTUBE = "YouTube"
        const val EMPTY = ""
    }

    interface OnYouTubeLinkClickListener {
        fun onClickYouTubeTrailerMovie(trailerUrl: String)
    }
}