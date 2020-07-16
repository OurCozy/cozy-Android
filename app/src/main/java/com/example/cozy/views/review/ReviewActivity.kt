package com.example.cozy.views.review

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.cozy.BottomItemDecoration
import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.RequestToServer.service
import com.example.cozy.network.customEnqueue
import com.example.cozy.network.responseData.AllReviewData
import com.example.cozy.tokenHeader
import com.example.cozy.views.map.MapDetailActivity
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.item_map_comment.*
import kotlin.properties.Delegates

class ReviewActivity : AppCompatActivity(){

    var bookIdx by Delegates.notNull<Int>()
    val requestToserver = RequestToServer
    lateinit var reviewAdapter: ReviewAdapter
    var data = mutableListOf<AllReviewData>()

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        if (intent.hasExtra("bookIdx")) {
            bookIdx = intent.getIntExtra("bookIdx",0)
        }

        showReview()

        /*tv_backicon.setOnClickListener {
            startActivity(Intent(this, MapDetailActivity::class.java))
        }*/

    }

    fun showReview(){
        val sharedPref = getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
        val header = mutableMapOf<String, String?>()
        header["Content-Type"] = "application/json"
        header["token"] = sharedPref.getString("token", "token")
        requestToserver.service.requestAllReview(20, header).customEnqueue(
            onError = {Toast.makeText(this,"올바르지 않은 요청입니다.",Toast.LENGTH_SHORT)},
            onSuccess = {
                Log.d("test", "성공")
                if(it.success) {
                    Log.d("test", it.message)
                    reviewAdapter = ReviewAdapter(this, it.data.toMutableList(),{})
                    rv_review.adapter = reviewAdapter
                    rv_review.addItemDecoration(BottomItemDecoration(this, 35))//itemDecoration 여백주기
                    Log.d("test", "성공")
                }
            }
        )

    }
}
