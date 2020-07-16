package com.example.cozy.network.responseData

data class ResponseRecent(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<RecentlySeenData>?
)

data class RecentlySeenData(
    val bookstoreIdx : Int,
    val bookstoreName : String,
    val profile : String,
    val image1 : String
)