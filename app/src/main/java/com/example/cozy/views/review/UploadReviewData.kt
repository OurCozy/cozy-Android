package com.example.cozy.views.review

data class UploadReviewData (
    val reviewIdx : Int,
    val userIdx : Int,
    val bookstoreIdx : Int,
    val content : String,
    val photo : String,
    val stars : Int,
    val createAt : String,
    val nickname : String,
    val profile : String
)