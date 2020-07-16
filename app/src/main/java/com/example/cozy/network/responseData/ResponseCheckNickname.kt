package com.example.cozy.network.responseData

data class ResponseCheckNickname(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : CheckNickname
)

data class CheckNickname(
    val nickname: String
)