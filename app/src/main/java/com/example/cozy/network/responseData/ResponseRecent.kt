package com.example.cozy.network.responseData

import com.example.cozy.views.mypage.RecentlySeenData

data class ResponseRecent (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<RecentlySeenData>
)