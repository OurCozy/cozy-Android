package com.example.cozy.views.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cozy.R
import kotlinx.android.synthetic.main.fragment_map.*

class MapFragment : Fragment() {

    lateinit var mapAdapter: MapAdapter
    val datas = mutableListOf<MapData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        viewPager.adapter= CustomPagerAdapter(childFragmentManager)
        home_viewpager.offscreenPageLimit=2
        tab.setupWithViewPager(home_viewpager)
        */

        mapAdapter =
            MapAdapter(view.context)
        bookstore.adapter = mapAdapter

        loadMapDatas()
        bookstore.addItemDecoration(MapItemDecoration()) //itemDecoration 여백주기

    }

    private fun loadMapDatas() {
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

        mapAdapter.datas = datas
        mapAdapter.notifyDataSetChanged()

    }
}
