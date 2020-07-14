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

class PopupFragment : BottomSheetDialogFragment(){

    override fun onStart() {
        super.onStart()

        popup_viewPager.adapter = PopupViewPagerAdapter(childFragmentManager)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        orange_btn.setOnClickListener {
//           val transaction = getFragmentManager()?.beginTransaction()
//            //transaction.replace(R.id.popup_viewPager, MapFragment.getInstance())
//            //Toast.makeText(this, "sksk", Toast.LENGTH_SHORT).show()
//        }

    }






}