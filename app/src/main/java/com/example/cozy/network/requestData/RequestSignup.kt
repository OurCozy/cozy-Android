package com.example.cozy.network.requestData

data class RequestSignup (
    val nickname : String,
    val email : String,
    val password : String,
    val passwordConfirm : String
)