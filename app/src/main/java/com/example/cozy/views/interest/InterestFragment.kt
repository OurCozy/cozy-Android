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
        val sharedPref = activity!!.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
        val header = mutableMapOf<String, String?>()
        header["Content-Type"] = "application/json"
        header["token"] = sharedPref.getString("token", "token")
        service.requestInterest(header).customEnqueue(
            onError = {Log.d("test", "error")},
            onSuccess = {
                if(it.success) {
                    iv_cock.text = it.data[0].nickname + "님의 콕!"
                    Log.d("test", "success")
                    interestAdapter = InterestAdapter(v.context, it.data.toMutableList()) { BookstoreInfo ->
                        val intent = Intent(activity, MapDetailActivity::class.java)
                        intent.putExtra("bookIdx",BookstoreInfo.bookstoreIdx)
                        startActivity(intent)
                    }
                    bookstore_interest.adapter = interestAdapter
                    background.visibility = View.GONE
                    tv_question.visibility = View.GONE
                } else {
                    bookstore_interest.visibility = View.GONE
                    background.visibility = View.VISIBLE
                    tv_question.visibility = View.VISIBLE
                    Log.d("test", "else")
                }
            }
        )
    }
}
