package com.example.cozy.views.interest

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cozy.R
import com.example.cozy.network.responseData.BookstoreInfo

class InterestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val rv_interest_title = itemView.findViewById<TextView>(R.id.rv_title)
    val hashTag1 = itemView.findViewById<TextView>(R.id.hashtag_map_tv1)
    val hashTag2 = itemView.findViewById<TextView>(R.id.hashtag_map_tv2)
    val hashTag3 = itemView.findViewById<TextView>(R.id.hashtag_map_tv3)
    val image = itemView.findViewById<ImageView>(R.id.rv_image)

    fun bind(data: BookstoreInfo){
        rv_interest_title.text = data.bookstoreName
        hashTag1.text = data.hashtag
        hashTag2.text = data.hashtag
        hashTag3.text = data.hashtag
        Glide.with(itemView).load("https://images.unsplash.com/photo-1561851561-04ee3d324423?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80").into(image)
    }
}
