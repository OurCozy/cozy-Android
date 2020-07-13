package com.example.cozy.views.map.popup

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.RequestToServer.service
import com.example.cozy.network.customEnqueue
import com.example.cozy.views.map.MapAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_seoul.*

class SeoulFragment : Fragment(){

    val service = RequestToServer.service
    lateinit var mapAdapter: MapAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
      return inflater.inflate(R.layout.fragment_seoul, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = activity!!.getSharedPreferences("sectionIdx", Context.MODE_PRIVATE)
        val editor = pref.edit()
        mapo.setOnClickListener{
            //val view = onInflate(R.layout.fragment_popup, )
            editor.putInt("mapo", 1)
            editor.commit()
            fragmentManager!!.beginTransaction().remove(PopupFragment()).commit()

        }

        /*val sharedPref = activity!!.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
        val header = mutableMapOf<String, String?>()
        header["Content-Type"] = "application/json"
        header["token"] = sharedPref.getString("token", "token")
            mapo.setOnClickListener{
                service.requestMap(1, header).customEnqueue(
                onError = {Toast.makeText(context!!,"false",Toast.LENGTH_SHORT)},
                onSuccess = {
                    if(it.success){

                       //bookstore.adapter = mapAdapter
                    }
                    else{
                        Toast.makeText(context!!,"false",Toast.LENGTH_SHORT)
                    }
                }
            )
        }*/

    }



}