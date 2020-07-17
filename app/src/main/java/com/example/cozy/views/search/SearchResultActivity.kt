package com.example.cozy.views.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cozy.BottomItemDecoration

import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.customEnqueue
import com.example.cozy.views.map.MapAdapter
import com.example.cozy.views.map.MapDetailActivity
import kotlinx.android.synthetic.main.activity_search_result.*

class SearchResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        rv_search_result.addItemDecoration(BottomItemDecoration(this, 15))

        val keyword : String = intent.getStringExtra("DATA")
        tv_search_keyword.text = keyword

        //검색 실행
        val header = mutableMapOf<String, String>()
        val sharedPref = getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
        header["Content-Type"] = "application/json"
        header["token"] = sharedPref.getString("token","token").toString()

        RequestToServer.service.requestSearch(keyword, header).customEnqueue(
            onError = { Log.d("test", "error")},
            onSuccess = {
                Log.d("test", it.message)
                if (it.success) {
                    val adapter = MapAdapter(this, it.data.toMutableList()) {MapData->
                        startActivity(Intent(this, MapDetailActivity::class.java))
                    }
                    rv_search_result.adapter = adapter
                }
            }
        )

        //돌아가기 버튼 클릭
        iv_back.setOnClickListener { finish() }

    }
}
