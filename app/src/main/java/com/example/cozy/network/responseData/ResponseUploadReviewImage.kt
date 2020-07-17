package com.example.cozy.network.responseData

data class ResponseUploadReviewImage (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : Photo?
)

data class Photo (
    val photo : String
)