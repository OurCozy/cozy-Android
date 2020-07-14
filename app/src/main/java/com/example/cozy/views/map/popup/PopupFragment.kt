package com.example.cozy.views.map.popup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.cozy.R
import com.example.cozy.views.map.MapFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_gg.*
import kotlinx.android.synthetic.main.fragment_popup.*
import kotlinx.android.synthetic.main.fragment_seoul.*

class PopupFragment(private val sectionIdx : (Int) -> Unit) : BottomSheetDialogFragment(){

    val popup = this

    override fun onStart() {
        super.onStart()

        popup_viewPager.adapter = PopupViewPagerAdapter(childFragmentManager,sectionIdx)
        popup_viewPager.offscreenPageLimit = 2

        tab_layout.setupWithViewPager(popup_viewPager)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        return inflater.inflate(R.layout.fragment_popup, container, false)
    }
}