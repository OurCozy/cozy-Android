package com.example.cozy.views.review

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.customEnqueue
import com.example.cozy.network.requestData.RequestUploadReview
import com.kakao.auth.authorization.AuthorizationResult
import kotlinx.android.synthetic.main.activity_put_review.*
import okhttp3.MultipartBody
import java.io.ByteArrayOutputStream

class PutReviewActivity : AppCompatActivity() {
    val IMAGE_FROM_GALLERY = 0
    var isStarFilled  = false
    var isTextFilled  = false
    var isImgFilled = false
    var star = 0
    lateinit var selectedImg : Uri
    lateinit var bitmap : Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_put_review)

        iv_close.setOnClickListener {
            finish()
        }

        star_1.setOnClickListener{ onClick(it) }
        star_2.setOnClickListener{ onClick(it) }
        star_3.setOnClickListener{ onClick(it) }
        star_4.setOnClickListener{ onClick(it) }
        star_5.setOnClickListener{ onClick(it) }

        findViewById<EditText>(R.id.et_review).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            //별점 입력했고 글 쓰는 중일 때 '게시' 메인컬러로 변경
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.length == 0) {
                    tv_next.setTextColor(resources.getColor(R.color.gray))
                    isTextFilled = false
                } else isTextFilled = true
                if(isStarFilled && isImgFilled)
                    tv_next.setTextColor(resources.getColor(R.color.mainColor))
            }
        })

        //이미지 업로드
        upload_image.setOnClickListener {
            startActivityForResult(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), IMAGE_FROM_GALLERY)
        }

        Log.d("test", intent.getIntExtra("bookIdx", 2).toString())
        tv_next.setOnClickListener {
            if(isStarFilled && isTextFilled && isImgFilled) {

                //서버 통신
                val sharedPref = this.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
                val header = mutableMapOf<String, String>()

                header["Content-Type"] = "multipart/form-data"
                header["token"] = sharedPref.getString("token", "token").toString()

                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()
                Log.d("test", ""+data)
                RequestToServer.service.requestUploadReviewImage(
                    intent.getIntExtra("bookIdx", 2),
                    MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("photo", data.toString()).build(),
                    header
                ).customEnqueue(
                    onError = {},
                    onSuccess = {
                        Log.d("test", it.data!!.photo)
                    }
                )

                header["Content-Type"] = "application/json"

                RequestToServer.service.requestUploadReview(
                    RequestUploadReview(
                        bookstoreIdx = intent.getIntExtra("bookIdx", 1),
                        content = et_review.text.toString(),
                        stars = star
                    ), header
                ).customEnqueue(
                    onError = {},
                    onSuccess = {
                        Log.d("test", it.message)
                    }
                )

                Toast.makeText(this, "후기가 등록되었습니다!", Toast.LENGTH_LONG).show()
                finish()
            } else Toast.makeText(this, "모든 항목을 입력해주세요!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //갤러리에서 선택한 사진 화면에 출력
        if(requestCode==IMAGE_FROM_GALLERY && resultCode==RESULT_OK && data!=null) {
            selectedImg = data!!.data!!
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImg)
            Log.d("Test", selectedImg.toString() +"   " + bitmap)
            upload_image.setImageBitmap(bitmap)
            isImgFilled = true
            if(isStarFilled && isTextFilled)
                tv_next.setTextColor(resources.getColor(R.color.mainColor))
        } else return
    }

    fun onClick(v: View) {
        when(v.id) {
            R.id.star_1 -> {
                star = 1
                isStarFilled = true
                if(isTextFilled && isImgFilled)
                    tv_next.setTextColor(resources.getColor(R.color.mainColor))
                star_1.setImageResource(R.drawable.ic_star_selected)
                star_2.setImageResource(R.drawable.ic_star)
                star_3.setImageResource(R.drawable.ic_star)
                star_4.setImageResource(R.drawable.ic_star)
                star_5.setImageResource(R.drawable.ic_star)
            }
            R.id.star_2 -> {
                star = 2
                isStarFilled = true
                if(isTextFilled && isImgFilled)
                    tv_next.setTextColor(resources.getColor(R.color.mainColor))
                star_1.setImageResource(R.drawable.ic_star_selected)
                star_2.setImageResource(R.drawable.ic_star_selected)
                star_3.setImageResource(R.drawable.ic_star)
                star_4.setImageResource(R.drawable.ic_star)
                star_5.setImageResource(R.drawable.ic_star)
            }
            R.id.star_3 -> {
                star = 3
                isStarFilled = true
                if(isTextFilled && isImgFilled)
                    tv_next.setTextColor(resources.getColor(R.color.mainColor))
                star_1.setImageResource(R.drawable.ic_star_selected)
                star_2.setImageResource(R.drawable.ic_star_selected)
                star_3.setImageResource(R.drawable.ic_star_selected)
                star_4.setImageResource(R.drawable.ic_star)
                star_5.setImageResource(R.drawable.ic_star)
            }
            R.id.star_4 -> {
                star = 4
                isStarFilled = true
                if(isTextFilled && isImgFilled)
                    tv_next.setTextColor(resources.getColor(R.color.mainColor))
                star_1.setImageResource(R.drawable.ic_star_selected)
                star_2.setImageResource(R.drawable.ic_star_selected)
                star_3.setImageResource(R.drawable.ic_star_selected)
                star_4.setImageResource(R.drawable.ic_star_selected)
                star_5.setImageResource(R.drawable.ic_star)
            }
            R.id.star_5 -> {
                star = 5
                isStarFilled = true
                if(isTextFilled && isImgFilled)
                    tv_next.setTextColor(resources.getColor(R.color.mainColor))
                star_1.setImageResource(R.drawable.ic_star_selected)
                star_2.setImageResource(R.drawable.ic_star_selected)
                star_3.setImageResource(R.drawable.ic_star_selected)
                star_4.setImageResource(R.drawable.ic_star_selected)
                star_5.setImageResource(R.drawable.ic_star_selected)
            }
        }
    }
}
