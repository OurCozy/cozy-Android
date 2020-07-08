<p align="center">
	<img src="/img/cozy_logo.png" width="200"/><br>
    <b style="font-size:40px;">COZY</b><br>
	바쁜 도심 속 휴식처, 독립서점
</p>
<br><br>

# Contents
* 뷰
	* [메인](#메인)
	* [지도](#지도)
	* [관심](#관심)
	* [내정보](#내정보)
* [프로젝트 구조](#구조)
* [라이브러리](#라이브러리)
* 기본 기능
	* [BottomNavigationView](#BottomNavigationView)
	* [RecyclerView](#RecyclerView)
* 주요 기능
	* [애니메이션](#애니메이션)
	* [카카오맵 API](#카카오맵)
* 확장함수
* 소스파일

# 뷰
<h2 id="메인">메인 화면</h2>

### 메인 상세 화면

<h2 id="지도">지도 화면</h2>

### 지도 상세 화면

<h2 id="관심">관심 화면</h2>
<h2 id="내정보">내정보 화면</h2>
<p align="center">
	<img src="/img/mypage_no_recently_seen.png" width="300"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<img src="/img/mypage.png" width="300"/><br>
</p>

사용자의 기본정보, 후기와 최근 책방 등을 볼 수 있는 화면이다. 왼쪽 화면은 사용자가 최근에 본 책방이 없을 때의 모습이고 오른쪽 화면은 최근에 본 책방을 RecyclerView로 보여준다. 아래의 코드와 같이 해당 데이터가 존재한다면 리사이클러뷰의 위에 위치한 TextView의 visibility를 Context.GONE 해주었다.

```kotlin
	if(data.size != 0) {
	    tv_no_recently_seen_background.visibility = GONE
	    tv_no_recently_seen_text.visibility = GONE
	} else {
		tv_no_recently_seen_background.visibility = VISIBLE
		tv_no_recently_seen_text.visibility = VISIBLE
	}
```

<br>

<p align="center">
	<img src="/img/mypage_blueprint.PNG" width="300"/><br>
</p>

<br>

화면의 레이아웃은 ConstraintLayout을 사용했다. 각 뷰들이 서로 유기적으로 연결되도록 제약을 주었다. 왼쪽으로 정렬된 뷰들은 각각 바로 위에 있는 뷰를 layout_constraintStart_toStartOf로 지정해줌으로써 별도로 marginLeft를 하지 않아도 정렬되도록했다. 화면 상단과 하단에 있는 가로줄의 width는 각각 match_parent와 0dp를 해주었고 하단 가로줄은 왼쪽과 오른쪽에 각각 23dp만큼 margin을 준 guideline을 만들어 제약을 주었다.

[xml 보러가기](https://github.com/OurCozy/cozy-Android/blob/master/app/src/main/res/layout/fragment_mypage.xml)

[목차로 돌아가기](#Contents)<br>

<h1 id="구조">프로젝트 구조</h1>

# 라이브러리

# 기본 기능

## BottomNavagationView

## RecyclerView

# 주요 기능

## 애니메이션

## 카카오맵

* 프레그먼트에 카카오맵 띄우기
* [카카오맵 실행하기](#kakaomap)

<p align="center">
	<img src="/img/kakao_map.png" width="300"/><br>
</p>

책방을 클릭했을 때 나오는 자세한 소개 뷰에서 카카오 API를 이용해 지도를 띄웠다. API를 사용하기 위해 [카카오 개발자 사이트](https://developers.kakao.com)에서 앱을 등록한 후, 필요한 라이브러리 파일을 추가하고 manifest에 아래와 같이 인터넷과 위치정보 접근을 허용시키고 발급받은 앱 키를 적어준다.

```xml
	<uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>

    <application>
        ...

        <meta-data android:name="com.kakao.sdk.AppKey" android:value="/*네이티브 앱 키*/"/>
    </application>
```
<br>

그 다음 아래와 같이 지도를 띄우기 위한 ViewGroup에 MapView를 연결한다. 카카오에서 제공하는 기능을 사용해 원하는 위도와 경도를 입력해 해당 위치의 지도를 보여줬고 마커를 표시해주었다.

```kotlin
class MapDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // id가 view_map인 ViewGroup에 맵 띄우기
        val mapView = MapView(activity)
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
        // 기본으로 제공하는 BluePin 마커 모양
        marker.markerType = MapPOIItem.MarkerType.BluePin 
        // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양
        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin 
        mapView.addPOIItem(marker)
    }
}
```

<p id="kakaomap" align="center">
	<img src="/img/open_map_no_app.gif" width="300"/><br>
	카카오맵이 설치되어 있지 않을 때<br><br><br>
	<img src="/img/open_map.gif" width="300"/><br><br>
	카카오맵이 설치되어 있을 때
</p>

마지막으로 길찾기 버튼을 클릭했을 때 카카오맵이 실행되도록 구현했다. packageManager.getLaunchIntentForPackage()로 해당 앱이 이미 설치되었는지 확인 후, 설치되어 있다면 앱을 연 다음 서점이 있는 곳을 보여주도록했고 앱이 설치되어있지 않다면 구글플레이에서 앱을 다운받을 수 있는 링크로 이동하도록했다.

``` kotlin
class MapDetailFragment : Fragment() {
    var packageName = "net.daum.android.map"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 길찾기 모양의 ImageView를 클릭했을 때 실행
        view.findViewById<ImageView>(R.id.iv_find_road).setOnClickListener {
        	// 앱이 설치 되어 있으면 앱 실행, 아니면 다운로드 링크로 연결
            if(isInstalledApp(packageName)) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("kakaomap://look?p=37.5602333,126.9225536"))
                startActivity(intent)
            } else {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
                startActivity(intent)
            }
        }
    }

    // 실행할 앱이 설치되어 있는지 확인
    fun isInstalledApp(packageName : String): Boolean {
        val intent = context!!.packageManager.getLaunchIntentForPackage(packageName)
        return intent != null
    }
}            
```

[프레그먼트에 카카오맵 띄우기](#카카오맵)

[Kotlin 코드 보러가기](https://github.com/OurCozy/cozy-Android/blob/master/app/src/main/java/com/example/cozy/views/map/MapDetailFragment.kt)

[목차로 돌아가기](#Contents)