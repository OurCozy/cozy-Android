package com.example.cozy.views.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.cozy.BottomItemDecoration
import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.customEnqueue
import com.example.cozy.network.responseData.BookstoreDetailData
import com.example.cozy.views.map.ReviewAdapter
import com.example.cozy.views.map.ReviewData
import kotlinx.android.synthetic.main.activity_recommend_detail.*
import kotlinx.android.synthetic.main.fragment_map_detail.rv_comments
import kotlinx.android.synthetic.main.fragment_map_detail.view_map
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import kotlin.properties.Delegates

class RecommendDetailActivity : AppCompatActivity() {

    lateinit var adapter: ReviewAdapter
    val requestTosever = RequestToServer
    var data = mutableListOf<ReviewData>()
    lateinit var detailData : BookstoreDetailData
    var bookIdx by Delegates.notNull<Int>()
    var latitude by Delegates.notNull<Double>()
    var longitude by Delegates.notNull<Double>()
    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4Ijo5LCJpYXQiOjE1OTQ0ODMwMjgsImV4cCI6My42MzYzNjM2MzYzNjM3OTU0ZSsyMiwiaXNzIjoib3VyLXNvcHQifQ.9TaQ-8Ck1kl15yxRzy2tF4Y20Ev5siFlsv9lKZxtVYQ"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend_detail)

        if (intent.hasExtra("bookIdx")) {
            bookIdx = intent.getIntExtra("bookIdx",0)
        }
        val header = mutableMapOf<String, String>()
        header["Content-Type"] = "application/json"
        header["token"] = token
        requestTosever.service.requestBookstore(bookIdx,header).customEnqueue(
            onError = {Toast.makeText(this,"올바르지 않은 요청입니다.",Toast.LENGTH_SHORT)},
            onSuccess = {
                detailData = it.data.elementAt(0)
                Glide.with(this).load(detailData.profile).into(rec_main_img)
                rec_intro1.text = detailData.shortIntro
                rec_intro2.text = detailData.shortIntro2
                rec_name.text = detailData.bookstoreName
                rec_address.text = detailData.location
                tv_bookstore_title.text = detailData.bookstoreName
                latitude = detailData.latitude
                longitude = detailData.longitude
                tv_1st_category.text = detailData.hashtag1
                tv_2nd_category.text = detailData.hashtag2
                tv_3rd_category.text = detailData.hashtag3
                tv_address.text = detailData.location
                time.text = detailData.time
                dayoff.text = detailData.dayoff
                changeable.text = detailData.changeable
                tel.text = detailData.tel
                activity.text = detailData.activity
                Glide.with(this).load(detailData.image1).into(iv_detail_img_1)
                Glide.with(this).load(detailData.image2).into(iv_detail_img_2)
                Glide.with(this).load(detailData.image3).into(iv_detail_img_3)
                tv_detail.text = detailData.description
                Log.d("data: ", detailData.profile)
            }
        )

        // 카카오 지도 API 사용 (AVD로 시행할 때는 25~51 주석처리하기)
//        val mapView = MapView(this)
//        val mapViewContainer = view_map as ViewGroup
//        mapViewContainer.addView(mapView)
//        // 서점 위치 위도&경도로 표시
//        val MARKER_POINT = MapPoint.mapPointWithGeoCoord(latitude, longitude)
//        mapView.setMapCenterPoint(MARKER_POINT, true)
//        // 지도 레벨 변경
//        mapView.setZoomLevel(3, true)
//        // 지도 위에 마커 표시
//        val marker = MapPOIItem()
//        marker.itemName = "Default Marker"
//        marker.tag = 0
//        marker.mapPoint = MARKER_POINT
//        marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.
//        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
//        mapView.addPOIItem(marker)
//
//        //카카오맵 실행 또는 구글플레이로 앱 검색
//        findViewById<ImageView>(R.id.iv_find_road).setOnClickListener {
//            if(isInstalledApp(packageName)) {
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("kakaomap://look?p=37.5602333,126.9225536"))
//                startActivity(intent)
//            } else {
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
//                startActivity(intent)
//            }
//        }
        adapter = ReviewAdapter(this)
        rv_comments.adapter = adapter
        loadData()
    }

    fun loadData() {
        data.apply{
            add(
                ReviewData(
                    userImg = "https://images.unsplash.com/photo-1593575391244-2f16fcf4cbf8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80",
                    userName = "코지마안마의자",
                    numOfStars = 5,
                    writtenDate = "2020년 7월 7일 15:35작성",
                    review = "안도북스는 제가 자주 가는 서점입니다. 안도북스의 분위기를 좋아하고, 책방 속의 작은 소품들과 따뜻한 조명이 퇴근후의 저를 위로하는 것 같아서 자주 방문하는 곳이에요. 퇴근하고 바로 달려가기 조금 빠듯한 시간이지만 자주 찾는 책방입니다. ",
                    addedImage = "https://images.unsplash.com/photo-1561851561-04ee3d324423?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80"
                )
            )
            add(
                ReviewData(
                    userImg = "https://images.unsplash.com/photo-1593575391244-2f16fcf4cbf8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80",
                    userName = "꼬북칩냠냠",
                    numOfStars = 4,
                    writtenDate = "2020년 7월 7일 15:35작성",
                    review = "안도북스에 가면 기분이 좋아져요 정형화 되지 않은 책 배열도 그렇고 서점이라기보단 친구집에 놀러간 느낌이 들어요. 책을 읽기 좋은 따뜻한 분위기를 만들어주는 것도 너무 좋고! 비오는날 스툴에 앉아서 창밖을 바라보며 책을 읽어보고싶네요. 비오는날은 좋아하지 않지만 안도북스에서는 괜찮을것 같아요.",
                    addedImage = "https://images.unsplash.com/photo-1532012197267-da84d127e765?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80"
                )
            )
        }
        adapter.data = data
        rv_comments.addItemDecoration(BottomItemDecoration(this, 35))
        adapter.notifyDataSetChanged()
    }

    // 실행할 앱이 설치되어 있는지 확인
    fun isInstalledApp(packageName : String): Boolean {
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        return intent != null
    }
}
