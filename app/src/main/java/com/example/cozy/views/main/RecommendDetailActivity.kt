package com.example.cozy.views.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.cozy.BottomItemDecoration
import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.customEnqueue
import com.example.cozy.network.responseData.BookstoreDetailData
import com.example.cozy.tokenHeader
import com.example.cozy.views.review.ReviewAdapter
import com.example.cozy.views.review.ReviewData
import com.example.cozy.views.review.PutReviewActivity
import kotlinx.android.synthetic.main.activity_recommend_detail.*
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import kotlin.properties.Delegates

class RecommendDetailActivity : AppCompatActivity() {

    lateinit var adapter: ReviewAdapter
    val service = RequestToServer.service
    var data = mutableListOf<ReviewData>()
    lateinit var detailData : BookstoreDetailData
    var bookIdx by Delegates.notNull<Int>()
    var latitude by Delegates.notNull<Double>()
    var longitude by Delegates.notNull<Double>()
    var kakaoPackageName : String = "net.daum.android.map"

    //관심책방 여부 저장 변수 TODO:서버에서 가져온 정보 여기에 저장
    var isChecked : Int = 0

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend_detail)

        if (intent.hasExtra("bookIdx")) {
            bookIdx = intent.getIntExtra("bookIdx",0)
        }

        /*
        val sharedPref = this.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
        val header = mutableMapOf<String, String>()
        header["Content-Type"] = "application/json"
        header["token"] = sharedPref.getString("token","token").toString()*/

        //서점 정보 불러오고
        //서점 위치 지도로 보여주기
        service.requestBookstore(bookIdx, tokenHeader(this)).customEnqueue(
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
                if (detailData.dayoff == "NULL"){
                    dayoff.visibility = View.GONE
                }
                else {
                    dayoff.text = detailData.dayoff
                }
                if (detailData.changeable == "NULL"){
                    changeable.visibility = View.GONE
                }
                else {
                    changeable.text = detailData.changeable
                }
                if (detailData.tel == "NULL"){
                    tel.text = "없음"
                }
                else {
                    tel.text = detailData.tel
                }
                if (detailData.activity == "NULL"){
                    activity.text = "없음"
                }
                else {
                    activity.text = detailData.activity
                }
                if (detailData.homepage == "NULL"){
                    rec_homepage.setImageResource(R.drawable.ic_homepage_non)
                }
                else {
                    rec_homepage.setOnClickListener{
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(detailData.homepage))
                        startActivity(intent)
                    }
                }
                if (detailData.facebook == "NULL"){
                    rec_facebook.setImageResource(R.drawable.ic_facebook_non)
                }
                else {
                    rec_facebook.setOnClickListener{
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(detailData.facebook))
                        startActivity(intent)
                    }
                }
                if (detailData.instagram == "NULL"){
                    rec_instagram.setImageResource(R.drawable.ic_insta_non)
                }
                else {
                    rec_instagram.setOnClickListener{
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(detailData.instagram))
                        startActivity(intent)
                    }
                }
                Glide.with(this).load(detailData.image1).into(iv_detail_img_1)
                Glide.with(this).load(detailData.image2).into(iv_detail_img_2)
                Glide.with(this).load(detailData.image3).into(iv_detail_img_3)
                tv_detail.text = detailData.description
                isChecked = detailData.checked
                iv_bookmark.isSelected = isChecked != 0
                Log.d("data: ", detailData.profile)

                // 카카오 지도 API 사용 (AVD로 실행할 때는 78~94 주석처리하기)
                val mapView = MapView(this)
                val mapViewContainer = rec_view_map as ViewGroup
                mapViewContainer.addView(mapView)
                // 서점 위치 위도&경도로 표시
                val MARKER_POINT = MapPoint.mapPointWithGeoCoord(latitude, longitude)
                mapView.setMapCenterPoint(MARKER_POINT, true)
                // 지도 레벨 변경
                mapView.setZoomLevel(3, true)
                // 지도 위에 마커 표시
                val marker = MapPOIItem()
                marker.itemName = "Default Marker"
                marker.tag = 0
                marker.mapPoint = MARKER_POINT
                marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.
                marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
                mapView.addPOIItem(marker)
            }
        )

        //카카오맵 실행 또는 구글플레이로 앱 검색
        findViewById<ImageView>(R.id.iv_find_road).setOnClickListener {
            if(isInstalledApp(kakaoPackageName)) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("kakaomap://look?p=$latitude,$longitude"))
                startActivity(intent)
            } else {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$kakaoPackageName"))
                startActivity(intent)
            }
        }

        val bookmarkImg = findViewById<ImageView>(R.id.iv_bookmark)

        // 관심책방 on/off
        bookmarkImg.setOnClickListener {
            //관심책방이면 체크해제
            if(isChecked != 0) {
                //서버에 해당 정보 전달
                service.requestBookmarkUpdate(bookIdx, tokenHeader(this)).customEnqueue(
                    onFail = { Toast.makeText(this, "관심책방 해제에 실패했습니다.", Toast.LENGTH_SHORT).show()},
                    onError = { Toast.makeText(this, "관심책방 해제에 실패했습니다.", Toast.LENGTH_SHORT).show()},
                    onSuccess = {
                        if(it.success) {
                            //색칠 안된 북마크로 이미지 변경
                            bookmarkImg.setImageResource(R.drawable.ic_bookmark)
                            isChecked = 0
                        } else {
                            Log.d("response", it.message)
                            Toast.makeText(this, "관심책방 해제에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            } else {
                //서버에 해당 정보 전달
                service.requestBookmarkUpdate(bookIdx, tokenHeader(this)).customEnqueue(
                    onFail = { Toast.makeText(this, "관심책방 등록에 실패했습니다.", Toast.LENGTH_SHORT).show()},
                    onError = { Toast.makeText(this, "관심책방 등록에 실패했습니다.", Toast.LENGTH_SHORT).show()},
                    onSuccess = {
                        if(it.success) {
                            //색칠된 북마크로 이미지 변경
                            bookmarkImg.setImageResource(R.drawable.ic_bookmark_selected)
                            isChecked = 1
                        } else {
                            Log.d("response", it.message)
                            Toast.makeText(this, "관심책방 등록에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }

        btn_write_review.setOnClickListener {
            startActivity(Intent(this, PutReviewActivity::class.java))
        }

        adapter = ReviewAdapter(this)
        rec_comments.adapter = adapter
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
        rec_comments.addItemDecoration(BottomItemDecoration(this, 35))
        adapter.notifyDataSetChanged()
    }

    // 실행할 앱이 설치되어 있는지 확인
    fun isInstalledApp(packageName : String): Boolean {
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        return intent != null
    }
}
