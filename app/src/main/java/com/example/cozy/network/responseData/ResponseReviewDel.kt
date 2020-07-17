package com.example.cozy.network.responseData

data class ResponseReviewDel(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : ReviewIdx
)

data class ReviewIdx(
    val reviewIdx : Int
)