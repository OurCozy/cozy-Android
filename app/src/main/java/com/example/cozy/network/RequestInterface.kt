package com.example.cozy.network

import com.example.cozy.network.responseData.ResponseInterest
import retrofit2.Call
import retrofit2.http.*

interface RequestInterface {
/*
    //회원가입 : https://github.com/OurCozy/cozy-server/wiki/1_1.-%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85
    @POST("/user/signup")
    fun requestSignup() : Call<ResponseSignup>

    //로그인

    //책방 8개 추천 : https://github.com/OurCozy/cozy-server/wiki/2_1.-%EC%B1%85%EB%B0%A9-8%EA%B0%9C-%EC%B6%94%EC%B2%9C
    @GET("/main/recommendation")
    fun requestRecommendation() : Call<ResponseRecommendation>

    //책방 자세히 : https://github.com/OurCozy/cozy-server/wiki/2_1.-%EC%B1%85%EB%B0%A9-%EC%9E%90%EC%84%B8%ED%9E%88
    @GET("/main/detail/{bookstoreIdx}")
    fun requestBookstroe(@Path("bookstoreIdx") bookstoreIdx: Int) : Call<ResponseBookstore>

    //후기 작성

    //지역별 조회 : https://github.com/OurCozy/cozy-server/wiki/2_2.-%EC%A7%80%EC%97%AD%EB%B3%84-%EC%A1%B0%ED%9A%8C
    @Headers("Content-Type:application/json")
    @GET("/main/map/{sectionIdx}")
    fun requestMap(@Path("sectionIdx") sectionIdx : Int) : Call<ResponseMap>
*/
    //관심 책방 조회 : https://github.com/OurCozy/cozy-server/wiki/2_3.-%EA%B4%80%EC%8B%AC%EC%B1%85%EB%B0%A9-%EC%A1%B0%ED%9A%8C
    @Headers(
        "Content-Type:application/json",
        "token:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjozLCJuaWNrbmFtZSI6ImdhaW4zIiwiaWF0IjoxNTk0MzcwMTk5LCJleHAiOjE1OTQzNzM3OTksImlzcyI6Im91ci1zb3B0In0.hB3D7azQe2oaRGlDPknwiRlxE8lsKN2XBz8rrqL_XjE"
    )
    @GET("/main/interest")
    fun requestInterest() : Call<ResponseInterest>
/*
    //북마크 업데이트 : https://github.com/OurCozy/cozy-server/wiki/2_3.-%EB%B6%81%EB%A7%88%ED%81%AC-%EC%97%85%EB%8D%B0%EC%9D%B4%ED%8A%B8
    @PUT("/main/interest/{bookstoreIdx}")
    fun requestBookmarkUpdate(@Path("bookstoreIdx") bookstoreIdx : Int) : Call<ResponseBookmarkUpdate>
*/
    //내 정보 조회

    //프로필 업데이트

    //내가 쓴 후기 조회

    //최근 본 책방 조회

    //키워드 검색
}