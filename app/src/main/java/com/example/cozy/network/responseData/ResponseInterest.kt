package com.example.cozy.network.responseData

data class ResponseInterest(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<BookstoreInfo>
)

data class BookstoreInfo(
    val bookstoreIdx : Int,
    val bookstoreName : String,
    val profile : String,
    val hashtag : String
)