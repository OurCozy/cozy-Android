package com.example.cozy.views.mypage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.customEnqueue
import com.example.cozy.views.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_mypage.*

class MypageFragment : Fragment() {
    lateinit var adapter: RecentlySeenAdapter
    var data = mutableListOf<RecentlySeenData>()
    val service = RequestToServer.service

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = context!!.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", "token")

        val header = mutableMapOf<String, String?>()
        header["Content-Type"] = "application/json"
        header["token"] = token

        //사용자 정보 불러오기
        service.requestUserInfo(header).customEnqueue(
            onError = { Log.d("response", "error")},
            onSuccess = {
                if(it.success) {
                    Log.d("response", it.message)
                    view.findViewById<TextView>(R.id.tv_user_name).text = it.data[0].nickname
                    tv_user_email.text = it.data[0].email
                    Glide.with(view).load(it.data[0].profile).into(rounded_iv_profile)
                }
            }
        )

        //검색창 열기
        activity!!.findViewById<ImageView>(R.id.iv_search).setOnClickListener {
            val intent = Intent(activity, SearchActivity::class.java)
            startActivity(intent)
        }

        adapter = RecentlySeenAdapter(view.context)
        rv_recently_seen.adapter = adapter
        loadData()
    }

    fun loadData() {
        data.apply {
            add(
                RecentlySeenData(
                    img = "https://images.unsplash.com/photo-1561851561-04ee3d324423?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80",
                    title = "퇴근길 책 한잔"
                )
            )
            add(
                RecentlySeenData(
                    img = "https://images.unsplash.com/photo-1532012197267-da84d127e765?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80",
                    title = "지구불시착"
                )
            )
            add(
                RecentlySeenData(
                    img = "https://images.unsplash.com/photo-1585066437544-6ca9b0e5bf1f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80",
                    title = "안도서점"
                )
            )
            add(
                RecentlySeenData(
                    img = "https://images.unsplash.com/photo-1571808161129-330529860c34?ixlib=rb-1.2.1&auto=format&fit=crop&w=969&q=80",
                    title = "스토리지북앤필름"
                )
            )
        }

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
