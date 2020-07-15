package com.example.cozy.signin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.cozy.R
import com.example.cozy.network.RequestSignup
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.customEnqueue
import com.example.cozy.textChangedListener
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_startpage.*
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

    val requestTosever = RequestToServer
    var isnickname = false
    var isemail = false
    var password = false
    var passwordcheck = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        signup_name.textChangedListener {
            if (it.isNullOrBlank()){
                isnickname = false
                signup_finish()
            }
            else{
                isnickname = true
                signup_finish()
            }
        }

        signup_email.textChangedListener {
            if (it.isNullOrBlank()){
                isemail = false
                signup_finish()
            }
            else{
                isemail = true
                signup_finish()
            }
        }

        signup_pw.textChangedListener {
            if(!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{10,20}$", signup_pw.text.toString())){
                pw_msg.text = "*영문, 숫자, 특수문자 포함 10~20자 입력해주세요."
                password = false
                signup_finish()
            }
            else{
                pw_msg.text=""
                password = true
                signup_finish()
            }

        }

        signup_pw_checked.textChangedListener {
            if (signup_pw.text.toString() == signup_pw_checked.text.toString()) {
                pw_check_msg.text = "*비밀번호가 일치합니다."
                pw_check_msg.setTextColor(ContextCompat.getColor(this, R.color.green))
                passwordcheck = true
                signup_finish()
            }
            else {
                pw_check_msg.text = "*비밀번호가 일치하지않습니다."
                pw_check_msg.setTextColor(ContextCompat.getColor(this, R.color.mainColor))
                passwordcheck = false
                signup_finish()
            }
        }

        finish_signup_button.setOnClickListener {
            requestTosever.service.requestSignup(
                RequestSignup(
                    nickname = signup_name.text.toString(),
                    email = signup_email.text.toString(),
                    password = signup_pw.text.toString(),
                    passwordConfirm = signup_pw_checked.text.toString()
                )
            ).customEnqueue(
                onError = {},
                onSuccess = {
                    if (it.success == false) {
                        pw_check_msg.text = it.message
                    }
                    else {
                        Toast.makeText(this,"가입이 완료되었습니다.",Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            )
        }

        //뒤로가기
        img_arrow_dark_signup.setOnClickListener {
            finish()
        }
    }

    fun signup_finish(){
        finish_signup_button.isEnabled = isnickname&&isemail&&password&&passwordcheck
    }
}
