
<h1 align="center">COZY_Android</h1>
<p align="center">
    <img src="/img/cozy_logo.png" width="200"/><br>
    바쁜 도심 속 휴식처,<br>공간과 경험을 파는 세상의 모든 독립서점
</p>
<br><br>

# Contents
* [Workflow](#Workflow)
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
    * [카카오 로그인](#카카오)
    * [Bottom-sheet Dialog](#BottomSheetDialog)
    * [textChangedListener](#textChangedListener)
    * [관심 책방 설정](#관심책방)
* 그 외 기능
    * [로그인 및 회원가입](#로그인)
    * [검색](#검색)
* 확장함수
    * [addOnPageChangeListener 확장함수](#확장)
    * [Kotlin Collection 활용](#Collection)
* 소스파일

<br>

# Workflow

<img src="/img/workflow.png"/><br>

## SOPT 26기 App-Jam
* 개발 기간 : 2020년 6월 28일 ~ 2020년 7월 18일


<br><br>
# 뷰
<h2 id="메인">메인 화면</h2>
<p align="center">
    <img src="/img/recommend.gif" width="300"/>
</p>

앱을 실행하고 로그인 했을 때 처음 나오는 메인 화면으로 독립서점을 총 8개를 추천해 주는 화면이다. 처음 가입했을 때는 임의로 뽑아놓은 8개의 독립서점이 뜬다. 사용자가 관심있는 책방에는 북마크를 할 수 있는데 나중에 이 추천뷰에는 사용자들이 북마크를 많이 한 순서대로 뜨게 된다.

<p align="center">
    <img src="/img/recommend_blueprint.JPG" width="300"/>
</p>

화면의 레이아웃은 전체 스크롤이 필요하여 NestedScrollView를 사용하였다. 전체 horizontal padding을 24dp로 주었다. width는 match_parent, height은 wrap_content를 사용하였고, NestedScrollView의 자식뷰는 일렬로 쭉 정렬되기 때문에 LinearLayout을 orientation을 vertical로 하여 사용하였다. 부모뷰에 horizontal padding을 줬기 때문에 width는 match_parent, height은 wrap_content를 사용하였다. 첫번째로는 logo와 serch아이콘이 들어가야하기 때문에 ConstraintLayout으로 chain을 spread_inside를 걸어줘서 사용하고 그 밑으로는 차례차례 TextView, RecyclerView를 넣었다. TextView는 width, height 둘 다 wrap_content를 주었고, RecyclerView는 width는 match_parent, height은 wrap_content를 사용하였다.

[xml 보러가기](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/res/layout/fragment_main.xml)

[목차로 돌아가기](#Contents)<br>

<p align="center">
    <img src="/img/recommend_item_blueprint.JPG" width="300"/>
</p>

메인 화면 Recyclerview의 itemview로 ConstraintLayout을 사용하였다. width는 match_parent, height은 값을  390dp로 고정하였다.

[xml 보러가기](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/res/layout/item_recommend.xml)

[목차로 돌아가기](#Contents)<br><br>

<h2 id="지도">지도 화면</h2>
<p align="center">
	<img src="/img/map_constraintLayout.PNG" width="200"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<img src="/img/map_image.PNG" width="200"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<img src="/img/map_popup.PNG" width="200"/><br>
</p>
- 사용자가 지역별로 책방을 찾을 수 있는 화면이다. default값인 마포구를 클릭했을 때 아래에서 위로 지역을 클릭할 수 있는 팝업창이 뜬다. 다른 지역을 클릭하게 되면 그에 따른 책방들이 RecyclerView로 보여진다. MapItemDecoration 에서 리사이클러뷰 아래에 getItemOffsets 함수를 사용해서 여백을 주었다.<br>
- 화면 레이아웃은 ConstraintLayout을 사용했다. 각 뷰 사이에 제약을 주면서 유기적으로 뷰가 움직일 수 있도록 만들었다. 양쪽에 guideline을 주어 여백을 따로 두지 않아도 되도록 만들었다.<br>

[xml 보러가기](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/res/layout/fragment_map.xml)

[목차로 돌아가기](#Contents)<br><br>

### 지도 상세 화면

<p align="center">
    <img src="/img/map_detail.gif" width="300"/><br><br>
    <img src="/img/map_detail_blueprint.PNG" width="300"/><br>
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

[목차로 돌아가기](#Contents)<br><br>

<h2 id="관심">관심 화면</h2>
<p align="center">
    <img src="/img/interest_none.PNG" width="300"/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="/img/interest.PNG" width="300"/>
</p>
- 각 책방에 있는 해시태그를 클릭해서 관심 등록한 책방이 보인다. 최신순으로 책방이 나타나며 관심이 있는 책방이 없을 경우 이 왼쪽과 같은 뷰가 뜬다. 이때 fragment_interest_none.xml가 호출될 수 있도록 코드를 작성한다. 지도뷰와 마찬가지로 관심있는 책방을 RecyclerView로 보여진다. MapItemDecoration 에서 리사이클러뷰 아래에 getItemOffsets 함수를 사용해서 여백을 주었다.<br>
- 화면 레이아웃은 ConstraintLayout을 사용했다. 각 뷰 사이에 제약을 주면서 유기적으로 뷰가 움직일 수 있도록 만들었다. 양쪽에 guideline을 주어 여백을 따로 두지 않아도 되도록 만들었다.<br>

[xml 보러가기](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/res/layout/fragment_interest.xml)

[목차로 돌아가기](#Contents)<br><br>

<h2 id="내정보">내 정보 화면</h2>
<p align="center">
	<img src="/img/mypage_no_recently_seen.jpg" width="300"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<img src="/img/mypage.jpg" width="300"/><br>
</p>

사용자의 기본정보, 후기와 최근 책방 등을 볼 수 있는 화면이다. 왼쪽 화면은 사용자가 최근에 본 책방이 없을 때의 모습이고 오른쪽 화면은 최근에 본 책방을 RecyclerView로 보여준다. 아래의 코드와 같이 해당 데이터가 존재한다면 리사이클러뷰의 위에 위치한 TextView의 visibility를 View.GONE 해주었다.

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
    <img src="/img/mypage_blueprint.JPG" width="300"/><br>
</p>

<br>

화면의 레이아웃은 ConstraintLayout을 사용했다. 각 뷰들이 서로 유기적으로 연결되도록 제약을 주었다. 왼쪽으로 정렬된 뷰들은 각각 바로 위에 있는 뷰를 layout_constraintStart_toStartOf로 지정해줌으로써 별도로 marginLeft를 하지 않아도 정렬되도록했다. 화면 상단과 하단에 있는 가로줄의 width는 각각 match_parent와 0dp를 해주었고 하단 가로줄은 왼쪽과 오른쪽에 각각 23dp만큼 margin을 준 guideline을 만들어 제약을 주었다. 내가 쓴 후기, 공지사항, 이벤트의 경우 텍스트와 화살표 이미지 외 가로에 있는 여백을 터치해도 해당 화면으로 넘어갈 수 있도록 별도의 width를 match_parent로 지정한 constraintLayout으로 묶었다.

[xml 보러가기](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/res/layout/fragment_mypage.xml)

[목차로 돌아가기](#Contents)<br><br>

<h1 id="구조">프로젝트 구조</h1>
<br>
<p align="center">
	<img src="/img/project.PNG" width="600"/><br>
</p>


# 라이브러리

``` kotlin
    //리사이클러뷰
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
    //material디자인 라이브러리
    implementation "com.google.android.material:material:1.2.0-alpha05"
    //이미지 로딩 라이브러리 : glide
    implementation "com.github.bumptech.glide:glide:4.10.0"
    kapt "com.github.bumptech.glide:compiler:4.10.0"
    //동그란 이미지 커스텀 뷰 라이브러리 : https://github.com/hdodenhof/CircleImageView
    implementation 'de.hdodenhof:circleimageview:3.1.0'
    //Retrofit 라이브러리 : https://github.com/square/retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.6.2'
    implementation 'com.squareup.retrofit2:retrofit-mock:2.6.2'
    //객체 시리얼라이즈를 위한 Gson 라이브러리 : https://github.com/google/gson
    implementation 'com.google.code.gson:gson:2.8.6'
    //Retrofit 에서 Gson 을 사용하기 위한 라이브러리
    implementation 'com.squareup.retrofit2:converter-gson:2.6.2'
    //디자인 라이브러리
    implementation 'com.android.support:design:28.0.0'
    //cardView 라이브러리
    implementation "androidx.cardview:cardview:1.0.0"
    //모서리 둥근 imageView 라이브러리 : https://github.com/vinc3m1/RoundedImageView
    implementation 'com.makeramen:roundedimageview:2.3.0'
    //Glide 적용 가능한 모서리 둥근 imageView 라이브러리 : https://github.com/rishabh876/RoundedImageView?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=7549
    implementation 'com.rishabhharit.roundedimageview:RoundedImageView:0.8.4'
    //카카오 API
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation files('libs/libDaumMapAndroid.jar')
    //bottom sheet 라이브러리
    implementation 'com.google.android.material:material:1.2.0-alpha02'
    // 카카오 로그인
    implementation group: 'com.kakao.sdk', name: 'usermgmt', version: '1.29.0'

```

# 기본 기능

## BottomNavigationView

화면 하단에 있는 4개의 탭을 만들기 위해 BottomNavigationView를 사용했다. 먼저 menu 태그로 각 item을 선언한 menu.xml을 다음과 같이 만들었다.

```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/menu_main"
        android:title="메인"
        android:icon="@drawable/ic_tab_main_selected" />
    <item
        ... />
</menu>
```

그 다음 BottomNavigation의 menu에 menu.xml를 적용해 4개의 탭 아이콘을 보여주고 labelVisibilityMode를 unlabeld로 지정해 title을 제외한 icon만 나오도록했다. 또한, bottom_selector로 state_selected의 상태에 따라 아이콘 색이 달라지게 구현했다.

```xml
<com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/navigation"
        app:menu="@menu/menu"
        app:itemIconTint="@color/bottom_selector"
        app:itemIconSize="50dp"
        app:itemTextColor="@color/bottom_selector"
        app:labelVisibilityMode="unlabeled"
        ... >
</com.google.android.material.bottomnavigation.BottomNavigationView>
```

또한 선택된 탭에 따라 ViewPager의 화면이 변경될 수 있도록 다음과 같이 리스너를 정의해 선택된 탭에 따라 ViewPager의 어뎁터에 정의해둔 화면이 보여질 수 있도록 구현했다.

```kotlin
    navigation.setOnNavigationItemSelectedListener {
        when(it.itemId) {
            R.id.menu_main -> viewPager.currentItem = 0
            R.id.menu_map -> viewPager.currentItem = 1
            R.id.menu_interest -> viewPager.currentItem = 2
            R.id.menu_mypage -> viewPager.currentItem = 3
        }
        true
    }
```

[목차로 돌아가기](#Contents)

## RecyclerView

리사이클러 뷰를 띄울 때는 서버에서 받아온 데이터를 받아왔다. 다음 코드와 같이 서버 통신에 성공하면 리사이클러 뷰의 어뎁터에 서버에서 받아온 데이터를 it.data.toMutableList()로 전달한다.

```kotlin
    RequestToServer.service.requestInterest(header).customEnqueue(
            onError = { /*에러 처리*/ },
            onSuccess = {
                if(it.success) {
                    //서버 통신 성공
                    //리사이클러 뷰에 받아온 데이터 전달
                    //뷰를 클릭했을 때 해당 서점 정보를 띄우는 MapDetailActivity를 실행하는 onClick(BookstoreInfo) 정의
                    interestAdapter = InterestAdapter(v.context, it.data.toMutableList()) { BookstoreInfo ->
                        val intent = Intent(activity, MapDetailActivity::class.java)
                        intent.putExtra("bookIdx",BookstoreInfo.bookstoreIdx)
                        startActivity(intent)
                    }
                    bookstore_interest.adapter = interestAdapter
                }
            }
        )
```

다음으로 어뎁터에서 onClick 함수를 다음과 같이 받는다.

```kotlin
class InterestAdapter (private val context: Context, val data : MutableList<MapData>, val onClick : (MapData) -> Unit) : RecyclerView.Adapter<InterestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterestViewHolder { ... }

    override fun getItemCount(): Int { ... }

    override fun onBindViewHolder(holder: InterestViewHolder, position: Int) { ... }
}
```

마지막으로 다음과 같이 뷰홀더에서 onClick 함수를 매개변수로 받고 리사이클러뷰의 아이템인 itemView의 setOnClickListener에서 onClick을 실행하면 Fragment에서 정의해준 함수의 body가 실행되어 새 액티비티가 실행된다.

```kotlin
class InterestViewHolder(itemView: View, val onClick: (MapData) -> Unit) : RecyclerView.ViewHolder(itemView) {
    ...

    fun bind(data: MapData){
        ...

        itemView.setOnClickListener {
            onClick(data)
        }
    }
}
```

[목차로 돌아가기](#Contents)

# 주요 기능

## 애니메이션

\*액티비티 전환 애니메이션
<p align="center">
    <img src="/img/recommend_animation.gif" width="300"/><br>
</p>

추천 탭에서 더 자세히 보고 싶은 책방을 눌렀을 때 똑같이 공유되는 요소 들을 부드럽게 보여주는 shared element transition의 효과를 적용했다. 먼저 fragment_main에서 공유되는 모든뷰에 transitionName을 지정해 준다.

```xml
    *fragment_main.xml의 item_recommend.xml
    <com.makeramen.roundedimageview.RoundedImageView
        ...
        android:transitionName="share_img1"
        ... />
    <com.makeramen.roundedimageview.RoundedImageView
        ...
        android:transitionName="share_img2"
        ... />
    <TextView
        ...
        android:transitionName="share_text1"
        ... />
    <TextView
        ...
        android:transitionName="share_text2"
        ... />
    <ImageView
        ...
        android:transitionName="share_icon"
        ... />
    <TextView
        ...
        android:transitionName="share_text3"
        ... />
    <TextView
        ...
        android:transitionName="share_text4"
    ... />
```
그리고 그 item을 눌렀을때 실행되는 RecommendDetailActivity의 activity_recommend_detail.xml에서도 fragment_main에서 똑같이 공유되는 뷰에만 transitionName을 **동일하게** 지정해 준다.

```xml
    *activity_recommend_detail.xml
    <ImageView
        ...
        android:transitionName="share_img1"
        ... />
    <ImageView
        ...
        android:transitionName="share_img2"
        ... />
    <TextView
        ...
        android:transitionName="share_text1"
        ... />
    <TextView
        ...
        android:transitionName="share_text2"
        ... />
    <ImageView
        ...
        android:transitionName="share_icon"
        ... />
    <TextView
        ...
        android:transitionName="share_text3"
        ... />
    <TextView
        ...
        android:transitionName="share_text4"
    ... />
```
그리고 MainFragment에서 item을 클릭했을때 실행되는 코드에 공유되는 뷰가 여러개일 때 view와 transitionName을 각각 Pair라는 클래스에 담아 ActionOptionsCompat의 makeSceneTransitionAnimation(Activity, Pair<F, S> ... Pair<F, S>)를 통해 애니메이션을 생성하였다.

```kotlin
    *MainFragment
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
```

[Kotlin 코드 보러가기]

[목차로 돌아가기](#Contents)

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
        val MARKER_POINT = MapPoint.mapPointWithGeoCoord(/*위도*/, /*경도*/)
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

<h2 id="카카오">카카오 로그인</h2>
<p align = "center">
    <img src="/img/kakao_signin.gif" width="300"/><br>
</p>

```kotlin
class SigninActivity : Activity() {

    val session = object : ISessionCallback {
        override fun onSessionOpenFailed(exception: KakaoException?) { ... }

        override fun onSessionOpened() {
            UserManagement.getInstance().me(object : MeV2ResponseCallback() {

                override fun onFailure(errorResult: ErrorResult?) { ... }

                override fun onSessionClosed(errorResult: ErrorResult?) { ... }

                //카카오 로그인 성공
                override fun onSuccess(result: MeV2Response?) {
                    //메인 화면으로 이동
                    openActivity()
                }
            })
        }
    }

    fun openActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    ...
}
```

[kotlin 코드 보러가기](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/java/com/example/cozy/signin/SigninActivity.kt)

[KakaoSdkProvider](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/java/com/example/cozy/signin/KakaoSdkProvider.kt)

[KakaoSdkAdapter](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/java/com/example/cozy/signin/KakaoSdkAdapter.kt)

reference: [카카오 개발자 가이드](https://developers.kakao.com/docs/latest/ko/kakaologin/android)

[목차로 돌아가기](#Contents)

<h2 id="확장">addOnPageChangeListener 확장함수</h2>

ViewPager의 addOnPageChangeListener를 확장함수로 구현하였다. onPageScrollStateChanged와 onPageScrolled는 따로 사용을 하지 않아서 Unit으로 해주고 onPageSelected만 onSelected(position)를 만들어줬다.

``` kotlin
    *OnPageChangeListener.kt
    fun ViewPager.OnPageChangeListener(onSelected: (Int) -> Unit){
    this.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) = Unit

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) = Unit

        override fun onPageSelected(position: Int) {
            onSelected(position)
        }
    })
}
```

MainActivity에서 onSelected를 구현해 주었다.

``` kotlin
    *MainActivity.kt
    viewPager.OnPageChangeListener {
            navigation.menu.getItem(it).isChecked = true
        }
}
```

[Kotlin 코드 보러가기]

[목차로 돌아가기](#Contents)

## Bottom-Sheet Dialog

지도 텍스트를 클릭했을 때 아래에서 위로 뜨는 팝업을 만들었다. bottomsheet를 띄우기 위해서 필요한 라이브러리 파일을 추가하고 이에 맞는 xml을 만들었다. tablayout을 사용해서
서울 경기를 나누었고 swipe을 위해서 viewpager를 만들었다. [xml 보러가기](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/res/layout/fragment_popup.xml) <br>
viewpager 안에는 [fragment_seoul.xml](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/res/layout/fragment_seoul.xml)
과 [fragment_gg.xml](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/res/layout/fragment_gg.xml) 를 띄운다.<br><br>

Mapfragment에 PopupFragment bottomsheet를 띄우기 위해서 getFragmentManager()로 객체를 가져와 프래그먼트를 사용할 수 있게 한다. sectionIdx는 어떤 구를 클릭했는지 값을 받아오기 위해 매개변수로 지정해놨다.

``` kotlin
location.setOnClickListener{
            val bottomsheet = PopupFragment(sectionIdx)
            getFragmentManager()?.let { it1 -> bottomsheet.show(it1, bottomsheet.tag) }
        }
```
<br>

PopupFragment에 또 다른 fragment를 띄우기 위해서  SeoulFragment와 GgFragment인 자식프래그먼트를 가지고 온다. 계속해서 sectionIdx 값을 가져온다.<br>

``` kotlin
override fun onStart() {
        super.onStart()

        popup_viewPager.adapter = PopupViewPagerAdapter(childFragmentManager,sectionIdx)
        popup_viewPager.offscreenPageLimit = 2

        tab_layout.setupWithViewPager(popup_viewPager)

    }
```
<br><br>
각 지역 버튼을 누를 때 부모 프래그먼트의 BottomSheetDialogFragment를 종료하고 sectionIdx에 값을 넣는다.
그리고 SeoulFragment에서 지역을 클릭했을 때 이 값에 따라서 이미지 색이 달라져야 하기 때문에 누른 sectionIdx 값을 sharedPreferenced에 저장한다.
이후 팝업을 내리고 다시 올려도 해당 지역 이미지 색이 달라질 수 있도록 한다.<br>
<p align="center">
	<img src="/img/bottomsheet.gif" width="300"/><br>
</p>

``` kotlin
//이미지 변경을 위한 선택된 데이터 저장
val pref = activity!!.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val ed = pref.edit()
        val location = pref.getInt("location",1)
        selectedLocation(location)
img_1.setOnClickListener{
    //부모 프래그먼트 종료(부모 프래그먼트에서 BottomSheetDialogFragment 가져오기)
            val popF = this.parentFragment as BottomSheetDialogFragment
            popF.dismiss()
            sectionIdx(1)
            ed.putInt("location", 1)
            ed.apply()
        }
```
[Kotlin 코드 보기](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/java/com/example/cozy/views/map/popup/SeoulFragment.kt)<br><br>
[목차로 돌아가기](#Contents)<br>

## textChangedListener

회원가입뷰에서 비밀번호 조건에 맞출 때, 비밀번호 일치 여부를 판단할 때 사용한다. 이 리스너는 텍스트를 입력할 때마다 리스너 이벤트가 작동한다. 비밀번호가 입력될 때마다 정규식을 통해서 판단하는데
숫자, 문자, 영문이 다 들어가야 조건이 맞도록 한다.<br>
 ``` kotlin
  signup_pw.textChangedListener {
            if(!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{10,20}$", signup_pw.text.toString())){
                pw_msg.text = "*영문, 숫자, 특수문자 포함 10~20자 입력해주세요."
                password = false
                signup_finish()
            }
 ```
 <br>
 비밀번호 확인 부분에 비밀번호를 입력했을 때 텍스트 감지해서 일치 여부를 띄운다.<br>

``` kotlin
  signup_pw_checked.textChangedListener {
            if (signup_pw.text.toString() == signup_pw_checked.text.toString()) {
                pw_check_msg.text = "*비밀번호가 일치합니다."
                pw_check_msg.setTextColor(ContextCompat.getColor(this, R.color.green))
                passwordcheck = true
                signup_finish()
            }
            else {
                pw_check_msg.text = "*비밀번호가 일치하지 않습니다."
                pw_check_msg.setTextColor(ContextCompat.getColor(this, R.color.mainColor))
                passwordcheck = false
                signup_finish()
            }
        }
```

[Kotlin 코드 보기](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/java/com/example/cozy/signin/SignupActivity.kt)<br><br>
[목차로 돌아가기](#Contents)<br>

<h2 id="관심책방">관심 책방 설정</h2>

<p align="center">
    <img src="/img/bookmark.gif" width="300"/><br>
</p>

리사이클러 뷰의 오른쪽에 위치한 책갈피 아이콘을 클릭하면 서버에 해당 서점의 관심 체크 여부를 PUT한다. 아이콘을 클릭할 때마다 활성화된 아이콘과 비활성화된 아이콘이 번갈아 나오도록 selector를 만들어주었다.

``` xml
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/ic_small_bookmark_selected"
        android:state_selected="true"/>
    <item android:drawable="@drawable/ic_small_bookmark"
        android:state_selected="false"/>
</selector>
```

리사이클러뷰의 북마크 아이콘을 클릭했을 때 해당 서점의 자세한 정보를 보여주는 화면으로 이동하지 않고 북마크 표시만 변경되도록 Adapter의 onBindViewHolder에서 해당 아이콘에 다음과 같이 클릭 리스너를 지정했다.

``` kotlin
    override fun onBindViewHolder(holder: InterestViewHolder, position: Int) {
        holder.bind(data[position])

        holder.bookmark.setOnClickListener {
            // 서버에 관심 책방 등록/해제 요청
            RequestToServer.service.requestBookmarkUpdate(data[position].bookstoreIdx, header).customEnqueue(
                onError = { /*에러 처리*/ },
                onSuccess = {
                    if(it.success) {
                        // 관심 책방 해제 성공하면 리사이클러 뷰에서 해당 아이템 제거
                        data.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, data.size)
                    }
                }
            )
        }
    }
```

[kotlin 코드 보러가기](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/java/com/example/cozy/views/interest/InterestAdapter.kt)

[목차로 돌아가기](#Contents)

# 그 외 기능

<h2 id="로그인">로그인 및 회원가입</h2>
<p align="center">
	<img src="/img/login.gif" width="300"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</p>
로그인은 이메일과 비밀번호 둘 중 하나 입력하지 않으면 완료버튼이 활성화 되지 않는다. 완료를 클릭했을 때 서버와 통신해서 이메일이 등록되어 있지 않은 회원이면 등록되지 않는 회원이라고 뜨고 이메일과 비밀번호가 일치하지 않으면 비밀번호가 일치하지 않으면 textview에 일치하지 않는다는 문구가 뜬다. <br><br>

<p align="center">
	<img src="/img/signup1.png" width="300"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<img src="/img/signup2.png" width="300"/>
</p>
닉네임, 이메일 중복을 서버와 통신하여 확인할 수 있으며 비밀번호 일치 여부를 판단해서 모든 조건을 갖추게 되면 완료 버튼이 활성화된다. 이 버튼을 누르게 되면 회원가입이 된다. <br><br>

[목차로 돌아가기](#Contents)
<br>

## 검색
<p align="center">
	<img src="/img/search.png" width="300"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<img src="/img/search2.png" width="300"/>
</p>
각 뷰에 검색 아이콘을 클릭하면 검책뷰가 뜬다. 검색하고자 하는 키워드를 입력하거나 해시태그를 클릭하면 그에 관련되는 독립서점이 뷰에 나온다.<br><br>

[목차로 돌아가기](#Contents)
<br>

<h2 id="Collection">Kotlin Collection 활용</h2>

서버 통신을 위한 헤더를 작성할 때 _mutableMapOf_ 을 활용했다. Map에는 Content-Type과 token을 각각 넣어 통신 요청했다.

```kotlin
    val sharedPref = activity.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
    val header = mutableMapOf<String, String?>()
    header["Content-Type"] = "application/json"
    header["token"] = sharedPref.getString("token", "token")
```

또 아래와 같이 서버로부터 반환값을 받기 위해 data를 List로 받았다.

```
// 서버 반환 형식
{
    "status": 200,
    "success": true,
    "message": "서점 리스트 조회 성공",
    "data": [
        {
            "bookstoreIdx": 1,
            "bookstoreName": "Piece",
            ...
        },
        {
            ...
        }
    ]
}
```

```kotlin
data class ResponseMap (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<MapData>
)

data class MapData(
    val sectionIdx : Int,
    val bookstoreIdx : Int,
    ...
)
```

[mutableMapOf 사용한 코드 있는 Kotlin 코드 보러가기](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/java/com/example/cozy/views/interest/InterestFragment.kt)

[List 사용한 ResponseMap 보러가기](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/java/com/example/cozy/network/responseData/ResponseMap.kt)

[List 사용한 MapData 보러가기](https://github.com/OurCozy/cozy-Android/blob/dev/app/src/main/java/com/example/cozy/views/map/MapData.kt)

[목차로 돌아가기](#Contents)
