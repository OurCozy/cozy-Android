package com.example.cozy.views.map

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.cozy.BottomItemDecoration
import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.customEnqueue
import com.example.cozy.views.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_map_detail.*
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MapDetailActivity : AppCompatActivity() {
    lateinit var adapter: ReviewAdapter
    var data = mutableListOf<ReviewData>()
    var kakaoPackageName : String = "net.daum.android.map"
    val service = RequestToServer.service

    //관심책방 여부 저장 변수 TODO:서버에서 가져온 정보 여기에 저장
    var isChecked : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_detail)

        // 카카오 지도 API 사용 (AVD로 실행할 때는 36~51 주석처리하기)
        val mapView = MapView(this)
        val mapViewContainer = view_map as ViewGroup
        mapViewContainer.addView(mapView)
        // 서점 위치 위도&경도로 표시
        val MARKER_POINT = MapPoint.mapPointWithGeoCoord(37.5602333, 126.9225536)
        mapView.setMapCenterPoint(MARKER_POINT, true)
        // 지도 레벨 변경
        mapView.setZoomLevel(3, true)
        // 지도 위에 마커 표시
        val marker = MapPOIItem()
        marker.itemName = "Default Marker"
        marker.tag = 0
        marker.mapPoint = MARKER_POINT
        marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양
        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양
        mapView.addPOIItem(marker)

        //카카오맵 실행 또는 구글플레이로 앱 검색
        findViewById<ImageView>(R.id.iv_find_road).setOnClickListener {
            if(isInstalledApp(kakaoPackageName)) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("kakaomap://look?p=37.5602333,126.9225536"))
                startActivity(intent)
            } else {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$kakaoPackageName"))
                startActivity(intent)
            }
        }

        // 창 닫기
        findViewById<ImageView>(R.id.iv_close).setOnClickListener {
            finish()
        }

        val bookmarkImg = findViewById<ImageView>(R.id.iv_bookmark)
        if(isChecked)
            bookmarkImg.setImageResource(R.drawable.ic_bookmark_selected)
        else bookmarkImg.setImageResource(R.drawable.ic_bookmark)

        // 관심책방 on/off
        bookmarkImg.setOnClickListener {
            val sharedPref = getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
            val header = mutableMapOf<String, String?>()
            header["Content-Type"] = "application/json"
            header["token"] = sharedPref.getString("token", "token")

            //관심책방이면 체크해제
            if(isChecked) {
                //서버에 해당 정보 전달
                //TODO: 서버에서 받은 bookstoreIdx 전달
                service.requestBookmarkUpdate(1, header).customEnqueue(
                    onError = { Log.d("response", "error")},
                    onSuccess = {
                        if(it.success) {
                            //색칠 안된 북마크로 이미지 변경
                            bookmarkImg.setImageResource(R.drawable.ic_bookmark)
                            isChecked = false
                        } else {
                            Log.d("response", it.message)
                            Toast.makeText(this, "관심책방 해제에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            } else {
                //서버에 해당 정보 전달
                //TODO: 서버에서 받은 bookstoreIdx 전달
                service.requestBookmarkUpdate(1, header).customEnqueue(
                    onError = { Log.d("response", "error")},
                    onSuccess = {
                        if(it.success) {
                            //색칠된 북마크로 이미지 변경
                            bookmarkImg.setImageResource(R.drawable.ic_bookmark_selected)
                            isChecked = true
                        } else {
                            Log.d("response", it.message)
                            Toast.makeText(this, "관심책방 등록에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }

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
