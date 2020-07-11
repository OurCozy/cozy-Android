package com.example.cozy.views.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cozy.R
import kotlin.properties.Delegates

class RecommendListVH(itemView: View, val itemClick: (RecommendListData, View) -> Unit) : RecyclerView.ViewHolder(itemView) {

    var bookstoreIdx by Delegates.notNull<Int>()
    var profile : ImageView = itemView.findViewById(R.id.rec_img)
    var text1 : TextView = itemView.findViewById(R.id.rec_text1)
    var text2 : TextView = itemView.findViewById(R.id.rec_text2)
    var name : TextView = itemView.findViewById(R.id.rec_name)
    var address : TextView = itemView.findViewById(R.id.rec_address)
    fun bind(myData: RecommendListData){
        bookstoreIdx = myData.bookstoreIdx
        Glide.with(itemView).load(myData.profile).into(profile)
        text1.text = myData.shortIntro
        text2.text = myData.shortIntro2
        name.text = myData.bookstoreName
        address.text = myData.location
        itemView.setOnClickListener{itemClick(myData, itemView)}
    }
}