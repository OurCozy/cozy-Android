package com.example.cozy.views.map

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cozy.R
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_map.view.*

class MapFragment : Fragment() {

    lateinit var mapAdapter: MapAdapter
    val datas = mutableListOf<MapData>()

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

        /*
        viewPager.adapter= CustomPagerAdapter(childFragmentManager)
        home_viewpager.offscreenPageLimit=2
        tab.setupWithViewPager(home_viewpager)
        */

        bookstore.addItemDecoration(MapItemDecoration()) //itemDecoration 여백주기
    }

    fun initMapList(view : View) {
        var mapAdapter = MapAdapter(view.context) { MapData ->
            var intent = Intent(activity, MapDetailActivity::class.java)
            startActivity(intent)
        }

        datas.apply {
            add(
                MapData(
                    rv_title = "퇴근 길 책 한 잔"
                )
            )

            add(
                MapData(
                    rv_title = "퇴근 길 책 한 잔"
                )
            )

            add(
                MapData(
                    rv_title = "퇴근 길 책 한 잔"
                )
            )
        }

        view.bookstore.adapter = mapAdapter
        mapAdapter.datas = datas
        mapAdapter.notifyDataSetChanged()
    }
}
