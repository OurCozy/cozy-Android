package com.example.cozy.views.interest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cozy.BottomItemDecoration
import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.customEnqueue
import com.example.cozy.views.map.MapDetailActivity
import com.example.cozy.views.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_interest.*
import kotlinx.android.synthetic.main.fragment_interest.background
import kotlinx.android.synthetic.main.fragment_interest.view.*
import kotlinx.android.synthetic.main.fragment_interest_none.*

class InterestFragment : Fragment() {
    val service = RequestToServer.service
    lateinit var interestAdapter: InterestAdapter
    lateinit var fragView : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragView = inflater.inflate(R.layout.fragment_interest, container, false)
        // Inflate the layout for this fragment
        return fragView
    }

    override fun onResume() {
        super.onResume()
        loadMapDatas(fragView)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //검색창 열기
        btn_search.setOnClickListener {
            startActivity(Intent(activity, SearchActivity::class.java))
        }

        bookstore_interest.addItemDecoration(BottomItemDecoration(this.context!!, 15)) //itemDecoration 여백주기
    }

    private fun loadMapDatas(v: View) {
        val header = mutableMapOf<String, String?>()
        val sharedPref = activity!!.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
        v.nickname_cock.text = sharedPref.getString("nickname", "cozy") + "님의 콕!"
        v.tv_question.text = sharedPref.getString("nickname", "cozy") + "님 만의"
        header["Content-Type"] = "application/json"
        header["token"] = sharedPref.getString("token", "token")
        service.requestInterest(header).customEnqueue(
            onError = {Log.d("test", "error")},
            onSuccess = {
                if(it.success) {
                    if(it.message == "서점 리스트 조회 성공") {
                        interestAdapter = InterestAdapter(v.context, it.data.toMutableList(), { onEmpty() }) { BookstoreInfo ->
                            val intent = Intent(activity, MapDetailActivity::class.java)
                            intent.putExtra("bookIdx",BookstoreInfo.bookstoreIdx)
                            startActivity(intent)
                        }
                        bookstore_interest.adapter = interestAdapter
                        background.visibility = View.GONE
                        tv_question.visibility = View.GONE
                        cock.visibility = View.GONE
                    } else onEmpty()
                } else onEmpty()
            }
        )
    }

    fun onEmpty() {
        cock.visibility = View.VISIBLE
        background.visibility = View.VISIBLE
        tv_question.visibility = View.VISIBLE
    }
}
