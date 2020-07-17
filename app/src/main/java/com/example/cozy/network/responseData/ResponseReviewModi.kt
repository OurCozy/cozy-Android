package com.example.cozy.network.responseData

data class ResponseReviewModi(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : AllReviewData
)