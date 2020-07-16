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
import com.example.cozy.views.map.MapData
import com.example.cozy.views.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_mypage.*

class MypageFragment : Fragment() {
    lateinit var adapter: RecentlySeenAdapter
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

        loadData()
    }

    override fun onResume() {
        super.onResume()

        loadData()
    }

    fun loadData() {
        val sharedPref = activity!!.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
        val header = mutableMapOf<String, String?>()
        header["Content-Type"] = "application/json"
        header["token"] = sharedPref.getString("token", "token")

        RequestToServer.service.requestRecent(header).customEnqueue(
            onError = {Log.d("test", "error")},
            onSuccess = {
                Log.d("test", "" + it.message)
//                if(it.success) {
//                    adapter = RecentlySeenAdapter(view!!.context, it.data.toMutableList())
//                    rv_recently_seen.adapter = adapter
//
//                    tv_no_recently_seen_background.visibility = GONE
//                    tv_no_recently_seen_text.visibility = GONE
//
//                    adapter.notifyDataSetChanged()
//                } else {
//                    tv_no_recently_seen_background.visibility = VISIBLE
//                    tv_no_recently_seen_text.visibility = VISIBLE
//                }
            }
        )
    }
}
