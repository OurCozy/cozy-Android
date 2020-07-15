package com.example.cozy.signin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cozy.R
import kotlinx.android.synthetic.main.activity_pw_search.*

class SearchPwdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pw_search)

        //뒤로가기
        img_arrow_dark.setOnClickListener { finish() }
    }
}
