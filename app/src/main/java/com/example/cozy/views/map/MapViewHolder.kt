package com.example.cozy.views.map

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cozy.R

class MapViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val rv_title = itemView.findViewById<TextView>(R.id.rv_title)

    fun bind(mapData: MapData){
        //Glide.with(itemView).load(mapData.)
        rv_title.text = mapData.rv_title
    }
}