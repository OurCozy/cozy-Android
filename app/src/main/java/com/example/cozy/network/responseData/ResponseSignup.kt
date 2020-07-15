package com.example.cozy.network.responseData

data class ResponseSignup (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : UserIdx
)
data class UserIdx(
    val userIdx : Int
)