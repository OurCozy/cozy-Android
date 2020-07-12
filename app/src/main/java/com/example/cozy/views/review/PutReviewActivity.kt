package com.example.cozy.views.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.example.cozy.R
import kotlinx.android.synthetic.main.activity_put_review.*

class PutReviewActivity : AppCompatActivity() {

    var isStarFilled  = false
    var isTextFilled  = false

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

        findViewById<EditText>(R.id.ed_review).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s!!.length == 0) {
                    tv_next.setTextColor(resources.getColor(R.color.gray))
                    isTextFilled = false
                } else isTextFilled = true
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            //별점 입력했고 글 쓰는 중일 때 '게시' 메인컬러로 변경
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(isStarFilled)
                    tv_next.setTextColor(resources.getColor(R.color.mainColor))
            }

        })
    }

    fun onClick(v: View) {
        when(v.id) {
            R.id.star_1 -> {

                isStarFilled = true
                if(isTextFilled)
                    tv_next.setTextColor(resources.getColor(R.color.mainColor))
                star_1.setImageResource(R.drawable.ic_star_selected)
                star_2.setImageResource(R.drawable.ic_star)
                star_3.setImageResource(R.drawable.ic_star)
                star_4.setImageResource(R.drawable.ic_star)
                star_5.setImageResource(R.drawable.ic_star)
            }
            R.id.star_2 -> {
                isStarFilled = true
                if(isTextFilled)
                    tv_next.setTextColor(resources.getColor(R.color.mainColor))
                star_1.setImageResource(R.drawable.ic_star_selected)
                star_2.setImageResource(R.drawable.ic_star_selected)
                star_3.setImageResource(R.drawable.ic_star)
                star_4.setImageResource(R.drawable.ic_star)
                star_5.setImageResource(R.drawable.ic_star)
            }
            R.id.star_3 -> {
                isStarFilled = true
                if(isTextFilled)
                    tv_next.setTextColor(resources.getColor(R.color.mainColor))
                star_1.setImageResource(R.drawable.ic_star_selected)
                star_2.setImageResource(R.drawable.ic_star_selected)
                star_3.setImageResource(R.drawable.ic_star_selected)
                star_4.setImageResource(R.drawable.ic_star)
                star_5.setImageResource(R.drawable.ic_star)
            }
            R.id.star_4 -> {
                isStarFilled = true
                if(isTextFilled)
                    tv_next.setTextColor(resources.getColor(R.color.mainColor))
                star_1.setImageResource(R.drawable.ic_star_selected)
                star_2.setImageResource(R.drawable.ic_star_selected)
                star_3.setImageResource(R.drawable.ic_star_selected)
                star_4.setImageResource(R.drawable.ic_star_selected)
                star_5.setImageResource(R.drawable.ic_star)
            }
            R.id.star_5 -> {
                isStarFilled = true
                if(isTextFilled)
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
