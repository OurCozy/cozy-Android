package com.example.cozy.signin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cozy.R
import kotlinx.android.synthetic.main.activity_startpage.*


class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startpage)

        email_login.setOnClickListener {
            startActivity(Intent(this, EmailLoginActivity::class.java))
        }

        signup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        //카카오 로그인
        btn_kakao_login.setOnClickListener {
            startActivity(Intent(this, SigninActivity::class.java))
        }

    }
}
