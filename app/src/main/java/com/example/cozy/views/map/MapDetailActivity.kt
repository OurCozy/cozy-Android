package com.example.cozy.views.map

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
import com.example.cozy.network.responseData.AllReviewData
import com.example.cozy.network.responseData.BookstoreDetailData

import com.example.cozy.tokenHeader
import com.example.cozy.views.review.PutReviewActivity
import com.example.cozy.views.review.ReviewActivity
import com.example.cozy.views.review.ReviewAdapter
import kotlinx.android.synthetic.main.activity_map_detail.*
import kotlinx.android.synthetic.main.activity_map_detail.btn_write_review
import kotlinx.android.synthetic.main.activity_map_detail.iv_bookmark
import kotlinx.android.synthetic.main.activity_map_detail.tv_more
import kotlinx.android.synthetic.main.activity_recommend_detail.*
import kotlinx.android.synthetic.main.activity_review.*
import net.daum.android.map.MapView
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import kotlin.properties.Delegates

class MapDetailActivity : AppCompatActivity() {

    val service = RequestToServer.service
    lateinit var detailData : BookstoreDetailData
    var bookIdx by Delegates.notNull<Int>()
    var latitude by Delegates.notNull<Double>()
    var longitude by Delegates.notNull<Double>()
    var kakaoPackageName : String = "net.daum.android.map"
    lateinit var reviewAdapter: ReviewAdapter
    var data = mutableListOf<AllReviewData>()

    //관심책방 여부 저장 변수 TODO:서버에서 가져온 정보 여기에 저장
    var isChecked : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_detail)

        if (intent.hasExtra("bookIdx")) {
            bookIdx = intent.getIntExtra("bookIdx",0)
        }


        val sharedPref = this.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
        val header = mutableMapOf<String, String>()
        header["Content-Type"] = "application/json"
        header["token"] = sharedPref.getString("token", "token").toString()

        //서점 정보 불러오고
        //서점 위치 지도로 보여주기
        service.requestBookstore(bookIdx, header).customEnqueue(
            onError = { Toast.makeText(this, "올바르지 않은 요청입니다.", Toast.LENGTH_SHORT) },
            onSuccess = {
                if(it.success){
                    detailData = it.data.elementAt(0)
                    if (detailData.profile == "NULL") {
                        Glide.with(this).load(detailData.image1).into(map_main_img)
                    }
                    else{
                        Glide.with(this).load(detailData.profile).into(map_main_img)
                    }
                    map_bookstore_title.text = detailData.bookstoreName
                    latitude = detailData.latitude
                    longitude = detailData.longitude
                    map_1st_category.text = detailData.hashtag1
                    map_2nd_category.text = detailData.hashtag2
                    map_3rd_category.text = detailData.hashtag3
                    map_address.text = detailData.location
                    map_time.text = detailData.time
                    if (detailData.dayoff == "NULL"){
                        map_dayoff.visibility = View.GONE
                    }
                    else {
                        map_dayoff.text = detailData.dayoff
                    }
                    if (detailData.changeable == "NULL"){
                        map_changeable.visibility = View.GONE
                    }
                    else {
                        map_changeable.text = detailData.changeable
                    }
                    if (detailData.tel == "NULL"){
                        map_tel.text = "없음"
                    }
                    else {
                        map_tel.text = detailData.tel
                    }
                    if (detailData.activity == "NULL"){
                        map_activity.text = "없음"
                    }
                    else {
                        map_activity.text = detailData.activity
                    }
                    if (detailData.homepage == "NULL"){
                        iv_homepage.setImageResource(R.drawable.ic_homepage_non)
                    }
                    else{
                        iv_homepage.setOnClickListener {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(detailData.homepage))
                            startActivity(intent)
                        }
                    }
                    if (detailData.facebook == "NULL"){
                        iv_facebook.setImageResource(R.drawable.ic_facebook_non)
                    }
                    else{
                        iv_facebook.setOnClickListener{
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(detailData.facebook))
                            startActivity(intent)
                        }
                    }
                    if (detailData.instagram == "NULL"){
                        iv_instagram.setImageResource(R.drawable.ic_insta_non)
                    }
                    else{
                        iv_instagram.setOnClickListener {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(detailData.instagram))
                            startActivity(intent)
                        }
                    }
                    isChecked = detailData.checked
                    if(detailData.checked == 1) iv_bookmark.isSelected = true
                    else iv_bookmark.isSelected = false
                    //iv_bookmark.isSelected = isChecked != 0
                    Glide.with(this).load(detailData.image1).into(map_detail_img_1)
                    Glide.with(this).load(detailData.image2).into(map_detail_img_2)
                    map_detail.text = detailData.description

//                    // 카카오 지도 API 사용 (AVD로 실행할 때는 78~94 주석처리하기)
//                    val mapView = net.daum.mf.map.api.MapView(this)
//                    val mapViewContainer = view_map as ViewGroup
//                    mapViewContainer.addView(mapView)
//                    // 서점 위치 위도&경도로 표시
//                    val MARKER_POINT = MapPoint.mapPointWithGeoCoord(latitude, longitude)
//                    mapView.setMapCenterPoint(MARKER_POINT, true)
//                    // 지도 레벨 변경
//                    mapView.setZoomLevel(3, true)
//                    // 지도 위에 마커 표시
//                    val marker = MapPOIItem()
//                    marker.itemName = "Default Marker"
//                    marker.tag = 0
//                    marker.mapPoint = MARKER_POINT
//                    marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.
//                    marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
//                    mapView.addPOIItem(marker)
                } else Log.d("test", "error")
            }
        )

        //카카오맵 실행 또는 구글플레이로 앱 검색
        findViewById<ImageView>(R.id.iv_find_road).setOnClickListener {
            if (isInstalledApp(kakaoPackageName)) {
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("kakaomap://look?p=$latitude,$longitude"))
                startActivity(intent)
            } else {
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$kakaoPackageName"))
                startActivity(intent)
            }
        }

            // 창 닫기
            findViewById<ImageView>(R.id.iv_close).setOnClickListener {
                finish()
            }

        val bookmarkImg = findViewById<ImageView>(R.id.iv_bookmark)

            // 관심책방 on/off
            bookmarkImg.setOnClickListener {
                /*
                val sharedPref = getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
                val header = mutableMapOf<String, String?>()
                header["Content-Type"] = "application/json"
                header["token"] = sharedPref.getString("token", "token")*/


                service.requestBookmarkUpdate(bookIdx, header).customEnqueue(
                    onError = { Log.d("response", "error")},
                    onSuccess = {
                        if(it.success) {
                            Log.d("response", it.message)
                            //북마크 이미지 변경
                            //bookmarkImg.setImageResource(R.drawable.ic_bookmark)
                            bookmarkImg.isSelected = !bookmarkImg.isSelected
                        } else {
                            Log.d("response", it.message)
                            Toast.makeText(this, "관심책방 등록/해제에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }

            btn_write_review.setOnClickListener {
                val intent = Intent(this, PutReviewActivity::class.java)
                intent.putExtra("bookIdx", bookIdx)
                startActivity(intent)
            }

            tv_more.setOnClickListener {
                val intent = Intent(this, ReviewActivity::class.java)
                intent.putExtra("bookIdx", bookIdx)
                startActivity(intent)
            }


        }

    override fun onResume() {
        super.onResume()
        showReview()
    }

    fun showReview(){
        val sharedPref = getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
        val header = mutableMapOf<String, String?>()
        header["Content-Type"] = "application/json"
        header["token"] = sharedPref.getString("token", "token")
        service.requestTwoReview(bookIdx, header).customEnqueue(
            onError = {Toast.makeText(this,"올바르지 않은 요청입니다.",Toast.LENGTH_SHORT)},
            onSuccess = {
                Log.d("test", "성공")
                if(it.success) {
                    Log.d("test", it.message)
                    reviewAdapter = ReviewAdapter(this, it.data.toMutableList(),{})
                    rv_comments.adapter = reviewAdapter
                    rv_comments.addItemDecoration(BottomItemDecoration(this, 35))//itemDecoration 여백주기
                    Log.d("test", "성공")
                }
            }
        )

    }

    // 실행할 앱이 설치되어 있는지 확인
    fun isInstalledApp(packageName : String): Boolean {
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        return intent != null
    }
}
