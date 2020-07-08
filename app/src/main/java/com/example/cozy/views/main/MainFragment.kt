package com.example.cozy.views.main

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.cozy.MainActivity
import com.example.cozy.R
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.item_recommend.*

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
            RecommendListAdapter(v.context){RecommendListData ->
                var intent = Intent(getActivity(),RecommendDetailActivity::class.java)
                var option : ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity as MainActivity,v,"represent")
                startActivity(intent,option.toBundle())
            }
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
