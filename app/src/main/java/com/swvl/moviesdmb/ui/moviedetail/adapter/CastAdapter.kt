package com.swvl.moviesdmb.ui.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.swvl.moviesdmb.R
import com.swvl.moviesdmb.databinding.CastsCardsBinding
import com.swvl.moviesdmb.models.Cast

class CastAdapter(
    private val castList: MutableList<Cast>
) : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder =
        CastViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.casts_cards,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = castList.size

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.castsCardsBinding.item = castList[position]
    }

    fun addCasts(casts: List<Cast>) {
        this.castList.apply {
            clear()
            addAll(casts)
        }
    }

    inner class CastViewHolder(val castsCardsBinding: CastsCardsBinding) :
        RecyclerView.ViewHolder(castsCardsBinding.root)
}