package com.example.cozy.views.map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.cozy.BottomItemDecoration
import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.RequestToServer.service
import com.example.cozy.network.customEnqueue
import com.example.cozy.views.map.popup.PopupFragment
import com.example.cozy.views.map.popup.SeoulFragment
import kotlinx.android.synthetic.main.fragment_map.*
import kotlin.properties.Delegates

class MapFragment : Fragment() {
    val service = RequestToServer.service
    lateinit var mapAdapter: MapAdapter
    lateinit var detailData : MapData
    lateinit var fragView: View
    var sectionIdx = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragView = inflater.inflate(R.layout.fragment_map, container, false)
        showMapList(fragView, sectionIdx)
        return fragView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionIdx = { num : Int ->
            showMapList(view,num)
            sectionIdx = num
        }

        location.setOnClickListener{
            val bottomsheet = PopupFragment(sectionIdx)
            getFragmentManager()?.let { it1 -> bottomsheet.show(it1, bottomsheet.tag) }
        }

        bookstore.addItemDecoration(BottomItemDecoration(this.context!!, 15)) //itemDecoration 여백주기
    }

    override fun onResume() {
        super.onResume()

        showMapList(fragView, sectionIdx)
    }

    fun showMapList(view : View, num : Int) {
        val sharedPref = activity!!.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
        val header = mutableMapOf<String, String?>()
        header["Content-Type"] = "application/json"
        header["token"] = sharedPref.getString("token", "token")
        service.requestMap(num, header).customEnqueue(
            onError = {Toast.makeText(context!!,"올바르지 않은 요청입니다.",Toast.LENGTH_SHORT)},
            onSuccess = {
                if(it.success) {
                    setSection(num)
                    detailData = it.data.elementAt(0)
                    store_count.text = detailData.count.toString()

                    mapAdapter = MapAdapter(view.context, it.data.toMutableList()) { MapData ->
                        val intent = Intent(activity, MapDetailActivity::class.java)
                        intent.putExtra("bookIdx",MapData.bookstoreIdx)
                        startActivity(intent)
                    }
                    bookstore.adapter = mapAdapter
                }
            }
        )
    }

    fun setSection(sectionIdx : Int){
        when(sectionIdx){
            1 -> {location.text = "마포구"; write_location.text = "마포구"}
            2 -> {location.text = "용산구"; write_location.text = "용산구"}
            3 -> {location.text = "관악구/영등포구"; write_location.text = "관악구/영등포구"}
            4 -> {location.text = "광진구/동대문구"; write_location.text = "광진구/동대문구"}
            5 -> {location.text = "강남구/서초구"; write_location.text = "강남구/서초구"}
            6 -> {location.text = "서대문구/종로구"; write_location.text = "서대문구/종로구"}
        }
    }
}
