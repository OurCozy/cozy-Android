package com.example.cozy.views.map.popup

import android.content.Context
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
        val pref = activity!!.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val ed = pref.edit()
        val location = pref.getInt("location",1)
        selectedLocation(location)

        img_1.setOnClickListener{
            val popF = this.parentFragment as BottomSheetDialogFragment
            popF.dismiss()
            sectionIdx(1)
            ed.putInt("location", 1)
            ed.apply()
        }
        img_2.setOnClickListener{
            img_2.setImageResource(R.drawable.img_map_square_orange)
            val popF = this.parentFragment as BottomSheetDialogFragment
            popF.dismiss()
            sectionIdx(2)
            ed.putInt("location", 2)
            ed.apply()
        }
        img_3.setOnClickListener{
            img_3.setImageResource(R.drawable.img_map_square_orange)
            val popF = this.parentFragment as BottomSheetDialogFragment
            popF.dismiss()
            sectionIdx(3)
            ed.putInt("location", 3)
            ed.apply()
        }
        img_4.setOnClickListener{
            img_4.setImageResource(R.drawable.img_map_square_orange)
            val popF = this.parentFragment as BottomSheetDialogFragment
            popF.dismiss()
            sectionIdx(4)
            ed.putInt("location", 4)
            ed.apply()
        }
        img_5.setOnClickListener{
            img_5.setImageResource(R.drawable.img_map_square_orange)
            val popF = this.parentFragment as BottomSheetDialogFragment
            popF.dismiss()
            sectionIdx(5)
            ed.putInt("location", 5)
            ed.apply()
        }
        img_6.setOnClickListener{
            img_6.setImageResource(R.drawable.img_map_square_orange)
            val popF = this.parentFragment as BottomSheetDialogFragment
            popF.dismiss()
            sectionIdx(6)
            ed.putInt("location", 6)
            ed.apply()
        }
    }


    fun selectedLocation(lc : Int){
        when(lc){
            1 -> {
                img_1.visibility = View.INVISIBLE
                img_1_orange.visibility = View.VISIBLE
                img_2.visibility = View.VISIBLE
                img_2_orange.visibility = View.INVISIBLE
                img_3.visibility = View.VISIBLE
                img_3_orange.visibility = View.INVISIBLE
                img_4.visibility = View.VISIBLE
                img_4_orange.visibility = View.INVISIBLE
                img_5.visibility = View.VISIBLE
                img_5_orange.visibility = View.INVISIBLE
                img_6.visibility = View.VISIBLE
                img_6_orange.visibility = View.INVISIBLE
            }
            2 -> {
                img_1.visibility = View.VISIBLE
                img_1_orange.visibility = View.INVISIBLE
                img_2.visibility = View.INVISIBLE
                img_2_orange.visibility = View.VISIBLE
                img_3.visibility = View.VISIBLE
                img_3_orange.visibility = View.INVISIBLE
                img_4.visibility = View.VISIBLE
                img_4_orange.visibility = View.INVISIBLE
                img_5.visibility = View.VISIBLE
                img_5_orange.visibility = View.INVISIBLE
                img_6.visibility = View.VISIBLE
                img_6_orange.visibility = View.INVISIBLE
            }
            3 -> {
                img_1.visibility = View.VISIBLE
                img_1_orange.visibility = View.INVISIBLE
                img_2.visibility = View.VISIBLE
                img_2_orange.visibility = View.INVISIBLE
                img_3.visibility = View.INVISIBLE
                img_3_orange.visibility = View.VISIBLE
                img_4.visibility = View.VISIBLE
                img_4_orange.visibility = View.INVISIBLE
                img_5.visibility = View.VISIBLE
                img_5_orange.visibility = View.INVISIBLE
                img_6.visibility = View.VISIBLE
                img_6_orange.visibility = View.INVISIBLE
            }
            4 -> {
                img_1.visibility = View.VISIBLE
                img_1_orange.visibility = View.INVISIBLE
                img_2.visibility = View.VISIBLE
                img_2_orange.visibility = View.INVISIBLE
                img_3.visibility = View.VISIBLE
                img_3_orange.visibility = View.INVISIBLE
                img_4.visibility = View.INVISIBLE
                img_4_orange.visibility = View.VISIBLE
                img_5.visibility = View.VISIBLE
                img_5_orange.visibility = View.INVISIBLE
                img_6.visibility = View.VISIBLE
                img_6_orange.visibility = View.INVISIBLE
            }
            5 -> {
                img_1.visibility = View.VISIBLE
                img_1_orange.visibility = View.INVISIBLE
                img_2.visibility = View.VISIBLE
                img_2_orange.visibility = View.INVISIBLE
                img_3.visibility = View.VISIBLE
                img_3_orange.visibility = View.INVISIBLE
                img_4.visibility = View.VISIBLE
                img_4_orange.visibility = View.INVISIBLE
                img_5.visibility = View.INVISIBLE
                img_5_orange.visibility = View.VISIBLE
                img_6.visibility = View.VISIBLE
                img_6_orange.visibility = View.INVISIBLE
            }
            6 -> {
                img_1.visibility = View.VISIBLE
                img_1_orange.visibility = View.INVISIBLE
                img_2.visibility = View.VISIBLE
                img_2_orange.visibility = View.INVISIBLE
                img_3.visibility = View.VISIBLE
                img_3_orange.visibility = View.INVISIBLE
                img_4.visibility = View.VISIBLE
                img_4_orange.visibility = View.INVISIBLE
                img_5.visibility = View.VISIBLE
                img_5_orange.visibility = View.INVISIBLE
                img_6.visibility = View.INVISIBLE
                img_6_orange.visibility = View.VISIBLE
            }
        }
    }


}