<h1 align="center">COZY_Android</h1>
<p align="center">
	<img src="/img/cozy_logo.png" width="200"/><br>
	바쁜 도심 속 휴식처, 공간과 경험을 파는 세상의 모든 독립서점
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
<p align="center">
	<img src="/img/recommend.jpg" width="300"/>
</p>

앱을 실행하고 로그인 했을 때 처음 나오는 메인 화면으로 독립서점을 총 8개를 추천해 주는 화면이다. 처음 가입했을 때는 임의로 뽑아놓은 8개의 독립서점이 뜬다. 사용자가 관심있는 책방에는 북마크를 할 수 있는데 나중에 이 추천뷰에는 사용자들이 북마크를 많이 한 순서대로 뜨게 된다.

<p align="center">
	<img src="/img/recommend_blueprint.JPG" width="300"/>
</p>

화면의 레이아웃은 전체 스크롤이 필요하여 NestedScrollView를 사용하였다. 전체 horizontal padding을 24dp로 주었다. width는 match_parent, height은 wrap_content를 사용하였고, NestedScrollView의 자식뷰는 일렬로 쭉 정렬되기 때문에 LinearLayout을 orientation을 vertical로 하여 사용하였다. 부모뷰에 horizontal padding을 줬기 때문에 width는 match_parent, height은 wrap_content를 사용하였다. 첫번째로는 logo와 serch아이콘이 들어가야하기 때문에 ConstraintLayout으로 chain을 spread_inside를 걸어줘서 사용하고 그 밑으로는 차례차례 TextView, RecyclerView를 넣었다. TextView는 width, height 둘 다 wrap_content를 주었고, RecyclerView는 width는 match_parent, height은 wrap_content를 사용하였다.

[xml 보러가기](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/res/layout/fragment_main.xml)

<p align="center">
	<img src="/img/recommend_item_blueprint.JPG" width="300"/>
</p>

메인 화면 Recyclerview의 itemview로 ConstraintLayout을 사용하였다. width는 match_parent, height은 값을  390dp로 고정하였다.

[xml 보러가기](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/res/layout/item_recommend.xml)

<h2 id="지도">지도 화면</h2>
<p align="center">
	  <img src="/img/map_constraintLayout.PNG" width="300"/>
	  <img src="/img/map_image.PNG" width="300"/>
	  <img src="/img/map_popup.PNG" width="300"/><br>
</p>
- 사용자가 지역별로 책방을 찾을 수 있는 화면이다. default값인 마포구를 클릭했을 때 아래에서 위로 지역을 클릭할 수 있는 팝업창이 뜬다. 다른 지역을 클릭하게 되면 그에 따른 책방들이 RecyclerView로 보여진다. MapItemDecoration 에서 리사이클러뷰 아래에 getItemOffsets 함수를 사용해서 여백을 주었다.<br>
- 화면 레이아웃은 ConstraintLayout을 사용했다. 각 뷰 사이에 제약을 주면서 유기적으로 뷰가 움직일 수 있도록 만들었다. 양쪽에 guideline을 주어 여백을 따로 두지 않아도 되도록 만들었다.<br>
					  

### 지도 상세 화면

<p align="center">
    <img src="/img/map_detail.gif" width="300"/><br><br>
    <img src="/img/map_detail_blueprint.png" width="300"/><br>
</p>

<br>

전체 레이아웃을 NestedScrollView로 감싸고 있는 형식으로 만들어 화면 스크롤이 가능하도록했다. 이미지의 경우 양옆 여백 없이 보여주고 있어 width는 match_parent를 주었고 height는 제약사항에 따라 적절히 변경되도록 0dp를 주었다. 서버로부터 들어오는 사진의 크기가 제각각일 수 있고 그에 따라 적절한 비율의 사진을 보여주기 위해 scaleType을 centerCrop하고 layout_constraintDimensionRatio에 사진의 비율을 입력했다. 예시 코드는 아래와 같다.

```xml
<ImageView
    android:id="@+id/iv_detail_img_1"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:scaleType="centerCrop"
    app:layout_constraintDimensionRatio="360:265"
    ...
    app:layout_constraintStart_toStartOf="@+id/iv_main_img"
    app:layout_constraintTop_toBottomOf="@+id/divide_line" />
```

이미지 외의 뷰는 모두 wrap_content를 해주고 서로 유기적으로 연결되도록 constraint를 주었다. 서점 이름 아래에 있는 세 개의 해시태그와 세 개의 SNS 아이콘은 각각 packed와 spread로 chainStyle을 적용한 HorizontalChain을 이용해 정렬했다. 후기를 보여주는 RecyclerView는 양옆에 23dp 만큼 margin을 준 guideline을 constraint로 지정하고 width 값을 0dp만큼 주어 양옆의 guideline에 꽉 차게 나오도록했다.

[xml 보러가기](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/res/layout/fragment_map_detail.xml)

[목차로 돌아가기](#Contents)<br>

<h2 id="관심">관심 화면</h2>
<p align="center">
	<img src="/img/interest_none.PNG" width="300"/>
	<img src="/img/interest.PNG" width="300"/>
</p>
- 각 책방에 있는 해시태그를 클릭해서 관심 등록한 책방이 보인다. 최신순으로 책방이 나타나며 관심이 있는 책방이 없을 경우 이 왼쪽과 같은 뷰가 뜬다. 이때 fragment_interest_none.xml가 호출될 수 있도록 코드를 작성한다. 지도뷰와 마찬가지로 관심있는 책방을 RecyclerView로 보여진다. MapItemDecoration 에서 리사이클러뷰 아래에 getItemOffsets 함수를 사용해서 여백을 주었다.<br>
- 화면 레이아웃은 ConstraintLayout을 사용했다. 각 뷰 사이에 제약을 주면서 유기적으로 뷰가 움직일 수 있도록 만들었다. 양쪽에 guideline을 주어 여백을 따로 두지 않아도 되도록 만들었다.<br>

<h2 id="내정보">내 정보 화면</h2>
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

화면의 레이아웃은 ConstraintLayout을 사용했다. 각 뷰들이 서로 유기적으로 연결되도록 제약을 주었다. 왼쪽으로 정렬된 뷰들은 각각 바로 위에 있는 뷰를 layout_constraintStart_toStartOf로 지정해줌으로써 별도로 marginLeft를 하지 않아도 정렬되도록했다. 화면 상단과 하단에 있는 가로줄의 width는 각각 match_parent와 0dp를 해주었고 하단 가로줄은 왼쪽과 오른쪽에 각각 23dp만큼 margin을 준 guideline을 만들어 제약을 주었다. 내가 쓴 후기, 공지사항, 이벤트의 경우 텍스트와 화살표 이미지 외 가로에 있는 여백을 터치해도 해당 화면으로 넘어갈 수 있도록 별도의 width를 match_parent로 지정한 constraintLayout으로 묶었다.

[xml 보러가기](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/res/layout/fragment_mypage.xml)

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_detail)

        // id가 view_map인 ViewGroup에 맵 띄우기
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
        // 기본으로 제공하는 BluePin 마커 모양
        marker.markerType = MapPOIItem.MarkerType.BluePin 
        // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양
        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin 
        mapView.addPOIItem(marker)
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
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_detail)

        // 길찾기 모양의 ImageView를 클릭했을 때 실행
        findViewById<ImageView>(R.id.iv_find_road).setOnClickListener {
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
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        return intent != null
    }
```

[프레그먼트에 카카오맵 띄우기](#카카오맵)

[Kotlin 코드 보러가기](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/java/com/example/cozy/views/map/MapDetailActivity.kt)

[목차로 돌아가기](#Contents)
