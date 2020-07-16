package com.example.cozy.views.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cozy.R
import com.example.cozy.views.map.MapData

class RecentlySeenAdapter(private val context: Context, val data : MutableList<RecentlySeenData>): RecyclerView.Adapter<RecentlySeenViewHolder>() {

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