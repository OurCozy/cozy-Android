package com.example.cozy.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.cozy.R
import com.example.cozy.network.*
import com.example.cozy.textChangedListener
import kotlinx.android.synthetic.main.activity_signup.*
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
                nickname_msg.text =""
                signup_finish()
            }
            else{
                isnickname = true
                signup_finish()
            }
        }

        //닉네임 중복 확인
        btn_nickname_overlap.setOnClickListener {
            requestTosever.service.requestCheckNickname(
                RequestCheckNickname(
                    nickname = signup_name.text.toString()
                )
            ).customEnqueue(
                onError = {},
                onSuccess = {
                    if(it.success == false){
                        nickname_msg.text = "*이미 사용중인 닉네임입니다."
                        nickname_msg.setTextColor(ContextCompat.getColor(this, R.color.mainColor))
                        isnickname = false
                        signup_finish()
                    }
                    else{
                        nickname_msg.text = "*사용 가능한 닉네임입니다."
                        nickname_msg.setTextColor(ContextCompat.getColor(this, R.color.green))
                        Log.d("test","사용가능")
                        isnickname = true
                        signup_finish()
                    }
                }

            )
        }

        signup_email.textChangedListener {
            if (it.isNullOrBlank()){
                isemail = false
                email_msg.text=""
                signup_finish()
            }
            else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(signup_email.text.toString()).matches()){
                email_msg.text = "*이메일 형식을 지켜주세요."
                email_msg.setTextColor(ContextCompat.getColor(this, R.color.mainColor))
                isemail = false
                signup_finish()
            }
            else{
                email_msg.text=""
                isemail = true
                signup_finish()
            }
        }

        btn_email_overlap.setOnClickListener {
            requestTosever.service.requestCheckEmail(
                RequestCheckEmail(
                    email = signup_email.text.toString()
                )
            ).customEnqueue(
                onError = {},
                onSuccess = {
                    if(it.success == false) {
                       email_msg.text = "*이미 사용중인 이메일 입니다."
                       email_msg.setTextColor(ContextCompat.getColor(this, R.color.mainColor))
                        isemail = false
                        signup_finish()
                    }
                    else {
                        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(signup_email.text.toString()).matches()){
                            email_msg.text = "*이메일 형식을 지켜주세요."
                            email_msg.setTextColor(ContextCompat.getColor(this, R.color.mainColor))
                            isemail = false
                            signup_finish()
                        }
                        else {
                            email_msg.text = "*사용 가능한 이메일 입니다."
                            email_msg.setTextColor(ContextCompat.getColor(this, R.color.green))
                            isemail = true
                            signup_finish()
                        }
                    }
                }
            )
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
                pw_check_msg.text = "*비밀번호가 일치하지 않습니다."
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
                    } else {
                        Toast.makeText(this, "가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            )
        }

        //뒤로가기
        img_arrow_dark_signup.setOnClickListener {
            startActivity(Intent(this, StartActivity::class.java))
        }
    }

    fun signup_finish(){
        finish_signup_button.isEnabled = isnickname&&isemail&&password&&passwordcheck
    }
}
