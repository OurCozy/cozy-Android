package com.example.cozy.views.interest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cozy.BottomItemDecoration
import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.customEnqueue
import com.example.cozy.network.responseData.ResponseInterest
import kotlinx.android.synthetic.main.fragment_interest.*
import retrofit2.Call
import retrofit2.Response

class InterestFragment : Fragment() {
    val service = RequestToServer.service

    lateinit var interestAdapter: InterestAdapter
    val datas = mutableListOf<InterestData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_interest, container, false)
        loadMapDatas(view)
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        viewPager.adapter= CustomPagerAdapter(childFragmentManager)
        home_viewpager.offscreenPageLimit=2
        tab.setupWithViewPager(home_viewpager)
        */

        bookstore_interest.addItemDecoration(BottomItemDecoration(this.context!!, 15)) //itemDecoration 여백주기
    }

    private fun loadMapDatas(v: View) {
        service.requestInterest().customEnqueue(
            onError = {},
            onSuccess = {
                if(it.success) {
                    interestAdapter = InterestAdapter(v.context, it.data)
                    bookstore_interest.adapter = interestAdapter
                }
            }
        )
    }

}
