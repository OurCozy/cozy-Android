package com.example.cozy.network.responseData

import com.example.cozy.views.map.MapData

data class ResponseMap (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<MapData>
)

