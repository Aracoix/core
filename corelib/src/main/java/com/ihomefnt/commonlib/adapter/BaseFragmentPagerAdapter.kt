package com.ihomefnt.commonlib.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class BaseFragmentPagerAdapter(fm: FragmentManager, private val frgList: ArrayList<Fragment>): FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment  = if(position>frgList.size)frgList[0] else frgList[position]
    override fun getCount(): Int = frgList.size
}