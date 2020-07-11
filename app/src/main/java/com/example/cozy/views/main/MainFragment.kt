package com.example.cozy.views.main

import android.content.Intent
import android.os.Bundle
import androidx.core.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import com.example.cozy.BottomItemDecoration
import com.example.cozy.MainActivity
import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.customEnqueue
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.item_recommend.view.*

class MainFragment : Fragment() {
    val requestTosever = RequestToServer
    val recommendData = mutableListOf<RecommendListData>()
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
            RecommendListAdapter(v.context){RecommendListData,View ->
                var intent = Intent(activity as MainActivity,RecommendDetailActivity::class.java)
                // shared element transition
                intent.putExtra("bookIdx",RecommendListData.bookstoreIdx)
                val imageViewPair1 = Pair.create<View, String>(View.rec_img, "share_img1")
                val imageViewPair2 = Pair.create<View, String>(View.rec_gradation, "share_img2")
                val textViewPair1 = Pair.create<View, String>(View.rec_text1, "share_text1")
                val textViewPair2 = Pair.create<View, String>(View.rec_text2, "share_text2")
                val imageViewPair3 = Pair.create<View, String>(View.icon_address, "share_icon")
                val textViewPair3 = Pair.create<View, String>(View.rec_name, "share_text3")
                val textViewPair4 = Pair.create<View, String>(View.rec_address, "share_text4")
                var option : ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity as MainActivity, imageViewPair1, imageViewPair2, textViewPair1, textViewPair2, imageViewPair3, textViewPair3, textViewPair4 )
                startActivity(intent,option.toBundle())
            }
        v.recommend_item.adapter = recommendAdapter
        requestTosever.service.requestRecommendation().customEnqueue(
            onError = {Toast.makeText(context!!,"올바르지 않은 요청입니다.",Toast.LENGTH_SHORT)},
            onSuccess = {
                recommendData.clear()
                recommendData.addAll(it.data)
                recommendAdapter.datas = recommendData
                v.recommend_item.addItemDecoration(BottomItemDecoration(this.context!!, 35))
                recommendAdapter.notifyDataSetChanged()
            }
        )
    }

}
