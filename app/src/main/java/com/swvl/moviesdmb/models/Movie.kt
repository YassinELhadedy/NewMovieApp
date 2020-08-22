package com.swvl.moviesdmb.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Movie(
    @PrimaryKey val id: Int,
    @ColumnInfo val overview: String,
    @ColumnInfo val releaseDate: String?,
    @ColumnInfo val posterPath: String?,
    @ColumnInfo val backdropPath: String?,
    @ColumnInfo val originalTitle: String,
    @ColumnInfo val title: String,
    @ColumnInfo val voteAverage: Double,
    @ColumnInfo val originalLanguage: String?,
    @ColumnInfo val pageId: Long? = null
) : Parcelable