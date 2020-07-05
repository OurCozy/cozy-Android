package com.example.cozy.views.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cozy.R

class RecentlySeenAdapter(private val context: Context): RecyclerView.Adapter<RecentlySeenViewHolder>() {
    var data = mutableListOf<RecentlySeenData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentlySeenViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recently_seen, parent, false)
        return RecentlySeenViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecentlySeenViewHolder, position: Int) {
        holder.bind(data[position])
    }

}