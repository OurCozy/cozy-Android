package com.example.cozy.network

data class RequestSignup (
    val nickname : String,
    val email : String,
    val password : String,
    val passwordConfirm : String
)