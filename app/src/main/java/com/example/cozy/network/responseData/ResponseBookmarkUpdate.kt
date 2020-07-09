package com.example.cozy.network.responseData

data class ResponseBookmarkUpdate(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : Checked?
)

data class Checked(
    val checked : Int
)