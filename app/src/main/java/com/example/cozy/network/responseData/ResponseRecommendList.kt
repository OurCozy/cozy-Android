package com.example.cozy.network.responseData

import com.example.cozy.views.main.RecommendListData

data class ResponseRecommendList(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<RecommendListData>
)