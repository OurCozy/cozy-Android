package com.example.cozy.views.map.popup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cozy.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_popup.*

class PopupFragment(private val sectionIdx : (Int) -> Unit) : BottomSheetDialogFragment(){

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