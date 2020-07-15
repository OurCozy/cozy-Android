package com.example.cozy.signin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.cozy.MainActivity
import com.example.cozy.R
import com.example.cozy.network.RequestLogin
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.customEnqueue
import com.example.cozy.textChangedListener
import kotlinx.android.synthetic.main.activity_email_login.*

class EmailLoginActivity() : AppCompatActivity() {

    val requestTosever = RequestToServer
    var isemail  = false
    var ispassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_login)

        email.textChangedListener{
            if (it.isNullOrBlank()){
                isemail = false
                login_finish()
            }
            else{
                isemail = true
                login_finish()
            }
    }
        password.textChangedListener {
            if (it.isNullOrBlank()) {
                ispassword = false
                login_finish()
            } else {
                ispassword = true
                login_finish()
            }
        }

        finish_button.setOnClickListener {
            if(email.text.isNullOrBlank()||password.text.isNullOrBlank()){
                star.visibility = View.VISIBLE
                error_message.text = "이메일, 혹은 비밀번호를 확인해주세요."
            }else{
                requestTosever.service.requestSignin(
                    RequestLogin(
                        email = email.text.toString(),
                        password = password.text.toString()
                    )
                ).customEnqueue(
                    onError = {},
                    onSuccess = {
                        if (it.success == false) {
                            star.visibility = View.VISIBLE
                            error_message.text = it.message
                        }
                        else {
                            var sharedPref = this.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
                            var editor = sharedPref.edit()
                            editor.putString("nickname", it.data.nickname)
                            editor.putString("token", it.data.accessToken)
                            editor.apply()
                            editor.commit()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                )
            }
        }
    }

    fun login_finish(){
        finish_button.isEnabled = isemail && ispassword
    }
}
