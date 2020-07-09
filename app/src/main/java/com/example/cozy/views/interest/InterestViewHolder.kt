package com.example.cozy.views.interest

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cozy.R

class InterestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val rv_interest_title = itemView.findViewById<TextView>(R.id.rv_title)

    fun bind(interestData: InterestData){
        rv_interest_title.text = interestData.rv_interest_title
    }
}
