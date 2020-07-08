package com.example.cozy.views.map

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cozy.R

class ReviewViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val userImg = itemView.findViewById<ImageView>(R.id.iv_profile)
    val userName = itemView.findViewById<TextView>(R.id.tv_user_name)
    val stars : Array<ImageView> = arrayOf(
        itemView.findViewById(R.id.iv_1st_star),
        itemView.findViewById(R.id.iv_2nd_star),
        itemView.findViewById(R.id.iv_3rd_star),
        itemView.findViewById(R.id.iv_4th_star),
        itemView.findViewById(R.id.iv_5th_star)
    )
    val writtenDate = itemView.findViewById<TextView>(R.id.tv_written_date)
    val review = itemView.findViewById<TextView>(R.id.tv_review)
    val addedImage = itemView.findViewById<ImageView>(R.id.iv_image)

    fun bind(data: ReviewData) {
        Glide.with(itemView).load(data.userImg).into(userImg)
        userName.text = data.userName
        for(num in 0 until stars.size) {
            if(num < data.numOfStars)
                Glide.with(itemView).load(R.drawable.ic_comments_star_selected).override(33,33).into(stars[num])
            else
                Glide.with(itemView).load(R.drawable.ic_comments_star).override(33,33).into(stars[num])
        }
        writtenDate.text = data.writtenDate
        review.text = data.review
        Glide.with(itemView).load(data.addedImage).into(addedImage)
    }
}