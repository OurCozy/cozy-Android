package com.example.cozy.views.mypage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.example.cozy.R
import kotlinx.android.synthetic.main.fragment_mypage.*

class MypageFragment : Fragment() {
    lateinit var adapter: RecentlySeenAdapter
    var data = mutableListOf<RecentlySeenData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RecentlySeenAdapter(view.context)
        rv_recently_seen.adapter = adapter
        loadData()
    }

    fun loadData() {
        data.apply {
            add(
                RecentlySeenData(
                    img = "https://images.unsplash.com/photo-1593895349007-8e6700308b3d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80",
                    title = "퇴근길 책 한잔"
                )
            )
            add(
                RecentlySeenData(
                    img = "https://images.unsplash.com/photo-1593366299134-3ef7cd2abbb8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=700&q=80",
                    title = "지구불시착"
                )
            )
        }
        Log.d("test2", data.size.toString())
        if(data.size != 0) {
            tv_no_recently_seen_background.visibility = GONE
            tv_no_recently_seen_text.visibility = GONE
        } else {
            tv_no_recently_seen_background.visibility = VISIBLE
            tv_no_recently_seen_text.visibility = VISIBLE
        }
        adapter.data = data
        adapter.notifyDataSetChanged()
    }
}
