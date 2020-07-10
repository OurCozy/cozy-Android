package com.example.cozy.views.map.popup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.cozy.R
import com.example.cozy.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_popup.*


class PopupActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_popup)


        popup_viewPager.adapter = PopupViewPagerAdapter(supportFragmentManager)
        popup_viewPager.offscreenPageLimit = 2

        tab_layout.setupWithViewPager(popup_viewPager)

    }
}