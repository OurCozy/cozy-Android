package com.example.cozy

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cozy.views.interest.InterestFragment
import com.example.cozy.views.main.MainFragment
import com.example.cozy.views.map.MapFragment
import com.example.cozy.views.mypage.MypageFragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> MainFragment()
            1 -> MapFragment()
            2 -> InterestFragment()
            else -> MypageFragment()
        }
    }

    override fun getCount(): Int = 4
}