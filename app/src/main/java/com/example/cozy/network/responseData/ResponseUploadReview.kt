package com.example.cozy.network.responseData

import com.example.cozy.views.review.UploadReviewData

data class ResponseUploadReview(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : UploadReviewData?
)