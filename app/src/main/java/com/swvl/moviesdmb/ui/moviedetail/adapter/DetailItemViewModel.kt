package com.swvl.moviesdmb.ui.moviedetail.adapter

import com.swvl.moviesdmb.models.Cast
import com.swvl.moviesdmb.models.Review
import com.swvl.moviesdmb.models.Trailer

data class DetailItemViewModel(
    val casts: List<Cast>, val reviews: List<Review>, val trailers: List<Trailer>
)