package com.example.cozy.views.mypage

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cozy.R
import com.example.cozy.network.responseData.RecentlySeenData

class RecentlySeenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val iv_img = itemView.findViewById<ImageView>(R.id.rounded_iv_img)
    val tv_title = itemView.findViewById<TextView>(R.id.tv_title)

    fun bind(data: RecentlySeenData) {
        Glide.with(itemView).load(data.image1).into(iv_img)
        tv_title.text = data.bookstoreName
    }
}