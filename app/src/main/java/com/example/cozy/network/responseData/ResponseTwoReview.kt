package com.example.cozy.network.responseData

data class ResponseTwoReview(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<AllReviewData>
)



