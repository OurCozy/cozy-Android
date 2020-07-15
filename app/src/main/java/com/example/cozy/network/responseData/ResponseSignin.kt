package com.example.cozy.network.responseData

data class ResponseSignin (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : TokenData
)
data class TokenData(
    val nickname : String,
    val accessToken : String
)