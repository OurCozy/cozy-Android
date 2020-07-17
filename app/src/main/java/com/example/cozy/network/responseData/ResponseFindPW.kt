package com.example.cozy.network.responseData

data class ResponseFindPW(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : SendEmail?
)

data class SendEmail(
    val toEmail : String,
    val subjct : String,
    val text : String
)