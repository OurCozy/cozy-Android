package com.example.cozy.network.requestData

data class RequestUploadReview(
    val bookstoreIdx : Int,
    val content : String,
    val stars: Int
)