package com.swvl.moviesdmb.models

import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("name") val cast_name: String,
    @SerializedName("character") val cast_character: String,
    @SerializedName("profile_path") val cast_poster: String,
    @SerializedName("id") val id: Int
)