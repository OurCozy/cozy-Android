package com.example.cozy.views.map.popup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cozy.R
import kotlinx.android.synthetic.main.fragment_gg.*

class GgFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gg, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orange_btn.setOnClickListener{
            //onBackPressed()
            fragmentManager!!.beginTransaction().remove(this).commit()
            //getFragmentManager()!!.popBackStack();

        }
    }
}