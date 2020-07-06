package com.example.cozy.views.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cozy.R
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.fragment_main.view.recommend_item

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        initRecommend(view)

        return view
    }

    private fun initRecommend(v : View){
        var recommendAdapter =
            RecommendListAdapter(v.context)
        recommendAdapter.apply{
            addItem(RecommendListData(R.drawable.img_content_photo, "다양한 독립서적과", "그림, 커피 한 잔까지?", "지구불시착", "서울특별시 노원구 화랑로 464"))
            addItem(RecommendListData(R.drawable.img_contents, "다양한 독립서적과", "그림, 커피 한 잔까지?", "지구불시착", "서울특별시 노원구 화랑로 464"))
            addItem(RecommendListData(R.drawable.img_content_photo, "다양한 독립서적과", "그림, 커피 한 잔까지?", "지구불시착", "서울특별시 노원구 화랑로 464"))
            addItem(RecommendListData(R.drawable.img_contents, "다양한 독립서적과", "그림, 커피 한 잔까지?", "지구불시착", "서울특별시 노원구 화랑로 464"))
            addItem(RecommendListData(R.drawable.img_content_photo, "다양한 독립서적과", "그림, 커피 한 잔까지?", "지구불시착", "서울특별시 노원구 화랑로 464"))
            addItem(RecommendListData(R.drawable.img_contents, "다양한 독립서적과", "그림, 커피 한 잔까지?", "지구불시착", "서울특별시 노원구 화랑로 464"))
            addItem(RecommendListData(R.drawable.img_content_photo, "다양한 독립서적과", "그림, 커피 한 잔까지?", "지구불시착", "서울특별시 노원구 화랑로 464"))
            addItem(RecommendListData(R.drawable.img_contents, "다양한 독립서적과", "그림, 커피 한 잔까지?", "지구불시착", "서울특별시 노원구 화랑로 464"))
        }

        v.recommend_item.adapter = recommendAdapter
        v.recommend_item.addItemDecoration(RecommendItemDecoration(this.context!!, 100))
        recommendAdapter.notifyDataSetChanged()
    }

}
