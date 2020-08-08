package com.swvl.moviesdmb.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("original_title") val originalTitle: String,
    val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("original_language") val originalLanguage: String?
) : Parcelable