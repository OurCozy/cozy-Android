package com.example.cozy

import android.app.Activity
import android.content.Context

fun tokenHeader(activity : Activity) : Map<String, String?>{
    val sharedPref = activity.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
    val header = mutableMapOf<String, String?>()
    header["Content-Type"] = "application/json"
    header["token"] = sharedPref.getString("token", "token")
    return header
}






