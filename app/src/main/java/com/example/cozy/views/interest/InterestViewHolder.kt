package com.example.cozy.views.interest

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cozy.R
import com.example.cozy.views.map.MapData


class InterestViewHolder(itemView: View, val onClick: (MapData) -> Unit) : RecyclerView.ViewHolder(itemView) {
    val rv_interest_title = itemView.findViewById<TextView>(R.id.rv_title)
    val hashTag1 = itemView.findViewById<TextView>(R.id.hashtag_map_tv1)
    val hashTag2 = itemView.findViewById<TextView>(R.id.hashtag_map_tv2)
    val hashTag3 = itemView.findViewById<TextView>(R.id.hashtag_map_tv3)
    val image = itemView.findViewById<ImageView>(R.id.rv_image)
    val bookmark = itemView.findViewById<ImageView>(R.id.rv_bookmark)

    fun bind(data: MapData){
        rv_interest_title.text = data.bookstoreName
        hashTag1.text = data.hashtag1
        hashTag2.text = data.hashtag2
        hashTag3.text = data.hashtag3
        if (data.profile == "NULL") {
            Glide.with(itemView).load(data.image1).into(image)
        }
        else{
            Glide.with(itemView).load(data.profile).into(image)
        }
        itemView.setOnClickListener {
            onClick(data)
        }
    }
}
