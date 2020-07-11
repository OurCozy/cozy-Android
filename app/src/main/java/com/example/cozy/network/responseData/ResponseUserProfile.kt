package com.example.cozy.network.responseData

data class ResponseUserProfile(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<UserProfile>
)

data class UserProfile(
    val nickname: String,
    val email: String,
    val profile: String
)