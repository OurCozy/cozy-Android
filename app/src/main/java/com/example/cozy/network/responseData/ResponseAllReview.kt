package com.example.cozy.network.responseData


data class ResponseAllReview(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<AllReviewData>

)

data class AllReviewData(
    val reviewIdx : Int,
    val userIdx : Int,
    val bookstoreIdx : Int,
    val content : String,
    val photo :String,
    val stars : Int,
    val createdAt : String,
    val nickname : String,
    val profile : String
)
