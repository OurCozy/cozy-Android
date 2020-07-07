package com.example.cozy.views.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.cozy.R
import kotlinx.android.synthetic.main.fragment_map_detail.*

class MapDetailFragment : Fragment() {
    lateinit var adapter: ReviewAdapter
    var data = mutableListOf<ReviewData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ReviewAdapter(view.context)
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
                    addedImage = "https://images.unsplash.com/photo-1514890547357-a9ee288728e0?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80"
                )
            )
            add(
                ReviewData(
                    userImg = "https://images.unsplash.com/photo-1593575391244-2f16fcf4cbf8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80",
                    userName = "꼬북칩냠냠",
                    numOfStars = 4,
                    writtenDate = "2020년 7월 7일 15:35작성",
                    review = "안도북스에 가면 기분이 좋아져요 정형화 되지 않은 책 배열도 그렇고 서점이라기보단 친구집에 놀러간 느낌이 들어요. 책을 읽기 좋은 따뜻한 분위기를 만들어주는 것도 너무 좋고! 비오는날 스툴에 앉아서 창밖을 바라보며 책을 읽어보고싶네요. 비오는날은 좋아하지 않지만 안도북스에서는 괜찮을것 같아요.",
                    addedImage = "https://images.unsplash.com/photo-1514890547357-a9ee288728e0?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80"
                )
            )
            add(
                ReviewData(
                    userImg = "https://images.unsplash.com/photo-1593575391244-2f16fcf4cbf8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80",
                    userName = "꼬지마안마의ja",
                    numOfStars = 3,
                    writtenDate = "2020년 7월 7일 15:35작성",
                    review = "안도북스에 가면 기분이 좋아져요 정형화 되지 않은 책 배열도 그렇고 서점이라기보단 친구집에 놀러간 느낌이 들어요. 책을 읽기 좋은 따뜻한 분위기를 만들어주는 것도 너무 좋고! 비오는날 스툴에 앉아서 창밖을 바라보며 책을 읽어보고싶네요. 비오는날은 좋아하지 않지만 안도북스에서는 괜찮을것 같아요.",
                    addedImage = "https://images.unsplash.com/photo-1514890547357-a9ee288728e0?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80"
                )
            )
            add(
                ReviewData(
                    userImg = "https://images.unsplash.com/photo-1593575391244-2f16fcf4cbf8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80",
                    userName = "꼬뿍이와또끼",
                    numOfStars = 2,
                    writtenDate = "2020년 7월 7일 15:35작성",
                    review = "안도북스에 가면 기분이 좋아져요 정형화 되지 않은 책 배열도 그렇고 서점이라기보단 친구집에 놀러간 느낌이 들어요. 책을 읽기 좋은 따뜻한 분위기를 만들어주는 것도 너무 좋고! 비오는날 스툴에 앉아서 창밖을 바라보며 책을 읽어보고싶네요. 비오는날은 좋아하지 않지만 안도북스에서는 괜찮을것 같아요.",
                    addedImage = "https://images.unsplash.com/photo-1514890547357-a9ee288728e0?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80"
                )
            )
        }
        adapter.data = data
        adapter.notifyDataSetChanged()
    }
}
