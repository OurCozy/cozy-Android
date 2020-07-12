package com.example.cozy.views.review

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.cozy.R
import kotlinx.android.synthetic.main.activity_put_star.*

class PutStarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_put_star)

        iv_close.setOnClickListener {
            finish()
        }

        star_1.setOnClickListener{ onClick(it) }
        star_2.setOnClickListener{ onClick(it) }
        star_3.setOnClickListener{ onClick(it) }
        star_4.setOnClickListener{ onClick(it) }
        star_5.setOnClickListener{ onClick(it) }
    }

    fun onClick(v: View) {
        when(v.id) {
            R.id.star_1 -> {
                tv_next.setTextColor(resources.getColor(R.color.mainColor))
                star_1.setImageResource(R.drawable.ic_star_selected)
                star_2.setImageResource(R.drawable.ic_star)
                star_3.setImageResource(R.drawable.ic_star)
                star_4.setImageResource(R.drawable.ic_star)
                star_5.setImageResource(R.drawable.ic_star)
            }
            R.id.star_2 -> {
                tv_next.setTextColor(resources.getColor(R.color.mainColor))
                star_1.setImageResource(R.drawable.ic_star_selected)
                star_2.setImageResource(R.drawable.ic_star_selected)
                star_3.setImageResource(R.drawable.ic_star)
                star_4.setImageResource(R.drawable.ic_star)
                star_5.setImageResource(R.drawable.ic_star)
            }
            R.id.star_3 -> {
                tv_next.setTextColor(resources.getColor(R.color.mainColor))
                star_1.setImageResource(R.drawable.ic_star_selected)
                star_2.setImageResource(R.drawable.ic_star_selected)
                star_3.setImageResource(R.drawable.ic_star_selected)
                star_4.setImageResource(R.drawable.ic_star)
                star_5.setImageResource(R.drawable.ic_star)
            }
            R.id.star_4 -> {
                tv_next.setTextColor(resources.getColor(R.color.mainColor))
                star_1.setImageResource(R.drawable.ic_star_selected)
                star_2.setImageResource(R.drawable.ic_star_selected)
                star_3.setImageResource(R.drawable.ic_star_selected)
                star_4.setImageResource(R.drawable.ic_star_selected)
                star_5.setImageResource(R.drawable.ic_star)
            }
            R.id.star_5 -> {
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
