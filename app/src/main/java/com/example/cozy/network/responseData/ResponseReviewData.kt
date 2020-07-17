package com.example.cozy.network.responseData

data class ResponseReviewData (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : rPhoto
)
data class rPhoto(
    val photo : String
)