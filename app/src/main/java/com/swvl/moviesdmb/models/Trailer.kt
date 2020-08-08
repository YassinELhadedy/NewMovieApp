package com.swvl.moviesdmb.models

import com.google.gson.annotations.SerializedName


data class Trailer(
    val id: String,
    val name: String,
    val site: String,
    @SerializedName("key") val videoId: String,
    val size: Int,
    val type: String
)