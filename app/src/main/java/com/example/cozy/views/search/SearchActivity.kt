package com.example.cozy.views.search

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.customEnqueue
import com.example.cozy.network.responseData.ResponseMap
import com.example.cozy.views.map.MapAdapter
import com.example.cozy.views.map.MapData
import com.example.cozy.views.map.MapDetailActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search_result.*
import java.net.URLEncoder

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //창 닫기
        iv_close.setOnClickListener {
            finish()
        }

        //엔터 누르면 검색하기
        findViewById<EditText>(R.id.et_search_bar).imeOptions = EditorInfo.IME_ACTION_SEARCH
        et_search_bar.setOnEditorActionListener { v, actionId, event ->
            when(actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {

                }
            }
            true
        }

        //검색 버튼 눌렀을 때 검색하기
        findViewById<ImageView>(R.id.iv_search).setOnClickListener {
            if(et_search_bar.text.toString().isNullOrBlank())
                Toast.makeText(this, "검색어를 입력하세요!", Toast.LENGTH_LONG).show()
            else {
                searchKeyword(et_search_bar.text.toString())
            }
        }

        tag1.setOnClickListener { searchKeyword(tag1.text.toString()) }
        tag2.setOnClickListener { searchKeyword(tag2.text.toString()) }
        tag3.setOnClickListener { searchKeyword(tag3.text.toString()) }
        tag4.setOnClickListener { searchKeyword(tag4.text.toString()) }
        tag5.setOnClickListener { searchKeyword(tag5.text.toString()) }
        tag6.setOnClickListener { searchKeyword(tag6.text.toString()) }
    }

    fun searchKeyword(text : String) {
        val intent = Intent(this, SearchResultActivity::class.java)
        intent.putExtra("DATA", text)
        startActivity(intent)
    }
}