package com.example.cozy.views.map.popup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.views.map.MapAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_seoul.*


class SeoulFragment(private val sectionIdx : (Int) -> Unit) : Fragment(){

    val service = RequestToServer.service

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
      return inflater.inflate(R.layout.fragment_seoul, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapo.setOnClickListener{
            val popF = this.parentFragment as BottomSheetDialogFragment
            popF.dismiss()
            sectionIdx(1)
        }
        yongsan.setOnClickListener{
            val popF = this.parentFragment as BottomSheetDialogFragment
            popF.dismiss()
            sectionIdx(2)
        }
        gwanak.setOnClickListener{
            val popF = this.parentFragment as BottomSheetDialogFragment
            popF.dismiss()
            sectionIdx(3)
        }
        gwangjin.setOnClickListener{
            val popF = this.parentFragment as BottomSheetDialogFragment
            popF.dismiss()
            sectionIdx(4)
        }
        kangnam.setOnClickListener{
            val popF = this.parentFragment as BottomSheetDialogFragment
            popF.dismiss()
            sectionIdx(5)
        }
        jongro.setOnClickListener{
            val popF = this.parentFragment as BottomSheetDialogFragment
            popF.dismiss()
            sectionIdx(6)
        }
    }
}