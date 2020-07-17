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
import com.example.cozy.network.responseData.AllReviewData
import com.example.cozy.network.responseData.BookstoreDetailData
import com.example.cozy.tokenHeader
import com.example.cozy.views.review.ReviewAdapter
import com.example.cozy.views.review.PutReviewActivity
import com.example.cozy.views.review.ReviewActivity
import kotlinx.android.synthetic.main.activity_recommend_detail.*
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import kotlin.properties.Delegates

class RecommendDetailActivity : AppCompatActivity() {


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
            val intent = Intent(this, PutReviewActivity::class.java)
            intent.putExtra("bookIdx", bookIdx)
            startActivity(intent)
        }

        tv_more.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putExtra("bookIdx", bookIdx)
            startActivity(intent)
        }

        rec_comments.addItemDecoration(BottomItemDecoration(this, 35))//itemDecoration 여백주기
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
                    rec_comments.adapter = reviewAdapter
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
