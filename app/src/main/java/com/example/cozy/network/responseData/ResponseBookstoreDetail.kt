package com.example.cozy.network.responseData

data class ResponseBookstoreDetail(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<BookstoreDetailData>
)

data class BookstoreDetailData(
    val bookstoreIdx : Int,
    val bookstoreName: String,
    val latitude: Double,
    val longitude: Double,
    val location: String,
    val sectionIdx: Int,
    val tel: String,
    val instagram: String,
    val facebook: String,
    val homepage: String,
    val blog: String,
    val time: String,
    val dayoff: String,
    val changeable: String,
    val activity: String,
    val shortIntro: String,
    val shortIntro2: String,
    val description: String,
    val bookmark: String,
    val profile: String,
    val hashtag1: String,
    val hashtag2: String,
    val hashtag3: String,
    val image1: String,
    val image2: String,
    val image3: String
)