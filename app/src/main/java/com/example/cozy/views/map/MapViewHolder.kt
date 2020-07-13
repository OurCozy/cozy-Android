package com.example.cozy.views.map

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cozy.R

class MapViewHolder(itemView: View, val onClick : (MapData) -> Unit) : RecyclerView.ViewHolder(itemView){

    val rv_interest_title = itemView.findViewById<TextView>(R.id.rv_title)
    val hashTag1 = itemView.findViewById<TextView>(R.id.hashtag_map_tv1)
    val hashTag2 = itemView.findViewById<TextView>(R.id.hashtag_map_tv2)
    val hashTag3 = itemView.findViewById<TextView>(R.id.hashtag_map_tv3)
    val image = itemView.findViewById<ImageView>(R.id.rv_image)
    val bookmark = itemView.findViewById<ImageView>(R.id.rv_hashtag)


    fun bind(data: MapData) {
        rv_interest_title.text = data.bookstoreName
        hashTag1.text = data.hashtag1
        hashTag2.text = data.hashtag2
        hashTag3.text = data.hashtag3
        Glide.with(itemView)
            .load("https://images.unsplash.com/photo-1561851561-04ee3d324423?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80")
            .into(image)
       /* Glide.with(itemView)
            .load()
            .into(bookmark)*/
        itemView.setOnClickListener {
            onClick(data)

        }
    }
}