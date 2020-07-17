package com.example.cozy.views.review

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cozy.R
import com.example.cozy.network.responseData.AllReviewData
import kotlin.properties.Delegates

class ReviewViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    var bookstoreIdx by Delegates.notNull<Int>()
    val profile = itemView.findViewById<ImageView>(R.id.iv_profile)
    val nickname = itemView.findViewById<TextView>(R.id.tv_user_name)
    val stars : Array<ImageView> = arrayOf(
        itemView.findViewById(R.id.iv_1st_star),
        itemView.findViewById(R.id.iv_2nd_star),
        itemView.findViewById(R.id.iv_3rd_star),
        itemView.findViewById(R.id.iv_4th_star),
        itemView.findViewById(R.id.iv_5th_star)
    )
    val createdAt = itemView.findViewById<TextView>(R.id.tv_written_date)
    val content = itemView.findViewById<TextView>(R.id.tv_content)
    val photo = itemView.findViewById<ImageView>(R.id.iv_image)
    val moreBtn = itemView.findViewById<ImageView>(R.id.iv_more)

    fun bind(data: AllReviewData) {
        bookstoreIdx = data.bookstoreIdx
        Glide.with(itemView).load(data.profile).into(profile)
        nickname.text = data.nickname
        for(num in 0 until stars.size) {
            if(num < data.stars)
                Glide.with(itemView).load(R.drawable.ic_comments_star_selected).override(33,33).into(stars[num])
            else
                Glide.with(itemView).load(R.drawable.ic_comments_star).override(33,33).into(stars[num])
        }
        createdAt.text = data.createdAt
        content.text = data.content
        Glide.with(itemView).load(data.photo).into(photo)
    }
}