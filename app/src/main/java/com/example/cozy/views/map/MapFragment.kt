package com.example.cozy.views.map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.cozy.BottomItemDecoration
import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.RequestToServer.service
import com.example.cozy.network.customEnqueue
import com.example.cozy.views.map.popup.PopupFragment
import kotlinx.android.synthetic.main.fragment_map.*

class MapFragment : Fragment() {
    val service = RequestToServer.service
    lateinit var mapAdapter: MapAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        initMapList(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        location.setOnClickListener{
            val bottomsheet = PopupFragment()
            getFragmentManager()?.let { it1 -> bottomsheet.show(it1, bottomsheet.tag) }
        }

        bookstore.addItemDecoration(BottomItemDecoration(this.context!!, 15)) //itemDecoration 여백주기
    }

    fun initMapList(view : View) {
        val sharedPref = activity!!.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
        val header = mutableMapOf<String, String?>()
        val pref = activity!!.getSharedPreferences("sectionIdx", Context.MODE_PRIVATE)
        val sectionIdx = pref.getInt("sedctionIdx", 2)
        Log.d("test", ""+sectionIdx)
        header["Content-Type"] = "application/json"
        header["token"] = sharedPref.getString("token", "token")
        service.requestMap(sectionIdx, header).customEnqueue(
            onError = {Toast.makeText(context!!,"올바르지 않은 요청입니다.",Toast.LENGTH_SHORT)},
            onSuccess = {
                if(it.success) {
                    mapAdapter = MapAdapter(view.context, it.data.toMutableList()) { MapData ->
                        val intent = Intent(activity, MapDetailActivity::class.java)
                        startActivity(intent)
                    }
                    bookstore.adapter = mapAdapter
                }
            }
        )
    }
}
