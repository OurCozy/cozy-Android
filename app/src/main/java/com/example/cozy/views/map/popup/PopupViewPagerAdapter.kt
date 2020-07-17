package com.example.cozy.views.map.popup

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class PopupViewPagerAdapter(fm: FragmentManager,private val sectionIdx : (Int) -> Unit) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> SeoulFragment(sectionIdx)
            else -> GgFragment()
        }

    }


    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0->"서울"
            else->{
                return "경기"
            }
        }
    }


}