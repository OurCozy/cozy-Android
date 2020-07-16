package com.example.cozy.network.responseData

data class ResponseCheckEmail (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : CheckEmail
)

data class CheckEmail(
    val email : String
)