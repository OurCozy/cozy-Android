package com.example.cozy.views.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cozy.R

class RecommendListVH(itemView: View, val itemClick: (RecommendListData) -> Unit) : RecyclerView.ViewHolder(itemView) {

    var image : ImageView = itemView.findViewById(R.id.rec_img)
    var text1 : TextView = itemView.findViewById(R.id.rec_text1)
    var text2 : TextView = itemView.findViewById(R.id.rec_text2)
    var name : TextView = itemView.findViewById(R.id.rec_name)
    var address : TextView = itemView.findViewById(R.id.rec_address)

    fun bind(myData: RecommendListData){
        image.setImageResource(myData.recommend_img)
        text1.text = myData.recommend_text1
        text2.text = myData.recommend_text2
        name.text = myData.recommend_name
        address.text = myData.recommend_address

        itemView.setOnClickListener{itemClick(myData)}
    }
}