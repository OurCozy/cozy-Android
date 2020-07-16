package com.example.cozy.signin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.customEnqueue
import com.example.cozy.network.requestData.RequestFindPW
import kotlinx.android.synthetic.main.activity_pw_search.*

class SearchPwdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pw_search)

        //비밀번호 찾기
        finish_button.setOnClickListener {
            RequestToServer.service.requestFindPW(RequestFindPW(
                email = email.text.toString()
            )).customEnqueue(
                onError = {},
                onSuccess = {
                    if(it.success) {
                        val builder = AlertDialog.Builder(this)
                        builder.setMessage("임시번호를 발송했습니다.")
                        builder.setPositiveButton("확인") {dialog, which->
                            Toast.makeText(this, "임시번호 발송 성공", Toast.LENGTH_LONG).show()
                        }

                        val dialog = builder.create()
                        dialog.show()
                    }
                }
            )
        }
        //뒤로가기
        img_arrow_dark.setOnClickListener { finish() }
    }
}
