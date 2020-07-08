package com.example.cozy.views.interest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cozy.R
import kotlinx.android.synthetic.main.fragment_interest.*

class InterestFragment : Fragment() {

    lateinit var interestAdapter: InterestAdapter
    val datas = mutableListOf<InterestData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_interest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        viewPager.adapter= CustomPagerAdapter(childFragmentManager)
        home_viewpager.offscreenPageLimit=2
        tab.setupWithViewPager(home_viewpager)
        */

        interestAdapter =
            InterestAdapter(view.context)
        bookstore_interest.adapter = interestAdapter

        loadMapDatas()
        bookstore_interest.addItemDecoration(InterestItemDecoration()) //itemDecoration 여백주기

    }

    private fun loadMapDatas() {
        datas.apply {
            add(
                InterestData(
                    rv_interest_title = "제목"
                )
            )

            add(
                InterestData(
                    rv_interest_title = "제목"
                )
            )

            add(
                InterestData(
                    rv_interest_title = "제목"
                )
            )
        }

        interestAdapter.datas = datas
        interestAdapter.notifyDataSetChanged()

    }

}
