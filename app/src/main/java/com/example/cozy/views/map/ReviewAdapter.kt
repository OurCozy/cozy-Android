package com.example.cozy.views.map

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cozy.R

class ReviewAdapter(private val context: Context) : RecyclerView.Adapter<ReviewViewHolder>() {
    var data = mutableListOf<ReviewData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_map_comment, parent, false)
        return ReviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(data[position])
    }

}