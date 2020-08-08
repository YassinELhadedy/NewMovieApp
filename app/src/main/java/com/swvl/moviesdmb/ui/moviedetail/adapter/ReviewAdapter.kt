package com.swvl.moviesdmb.ui.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.swvl.moviesdmb.R
import com.swvl.moviesdmb.databinding.ReviewCardsBinding
import com.swvl.moviesdmb.models.Review

class ReviewAdapter(private val reviewsList: MutableList<Review>) :
    RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewAdapter.ReviewViewHolder =
        ReviewViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.review_cards,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = reviewsList.size

    override fun onBindViewHolder(holder: ReviewAdapter.ReviewViewHolder, position: Int) {
        holder.reviewCardsBinding.item = reviewsList[position]
        holder.reviewCardsBinding.descriptionReviews.text = reviewsList[position].content
    }

    fun addReviews(reviews: List<Review>) {
        this.reviewsList.apply {
            clear()
            addAll(reviews)
        }
    }

    inner class ReviewViewHolder(val reviewCardsBinding: ReviewCardsBinding) :
        RecyclerView.ViewHolder(reviewCardsBinding.root)
}