package com.swvl.moviesdmb.ui.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.swvl.moviesdmb.BuildConfig
import com.swvl.moviesdmb.R
import com.swvl.moviesdmb.models.Trailer
import com.swvl.moviesdmb.ui.moviedetail.MovieDetailFragment
import com.swvl.moviesdmb.ui.moviedetail.adapter.TrailerAdapter


@BindingAdapter("image")
fun loadImage(view: ImageView, url: String) {

    Glide.with(view).load(BuildConfig.TMDB_PHOTO_BASE_URL.plus(url)).into(view)
}

@BindingAdapter("castImage")
fun loadCastImage(view: ImageView, url: String) {

    Glide.with(view).load(BuildConfig.TMDB_PHOTO_BASE_URL.plus(url))
        .placeholder(R.drawable.cast_icon)
        .into(view)
}

@BindingAdapter("youTubeImage")
fun loadYouTubeImage(view: ImageView, trailer: Trailer) {
    val options = RequestOptions()
        .placeholder(R.color.colorPrimary)
        .centerCrop()
        .override(150, 150)
    Glide.with(view).load(getThumbnailUrl(trailer))
        .placeholder(R.drawable.youtube).apply(options)
        .into(view)
}

@BindingAdapter("android:visibility")
fun setVisibility(view: ProgressBar, value: String) {
    view.visibility = if (value.isEmpty()) View.VISIBLE else View.GONE
}

fun getThumbnailUrl(trailer: Trailer): String? {
    return if (TrailerAdapter.SITE_YOUTUBE.equals(trailer.site, ignoreCase = true)) {
        String.format(MovieDetailFragment.YOUTUBE_THUMBNAIL_URL, trailer.videoId)
    } else {
        TrailerAdapter.EMPTY
    }
}