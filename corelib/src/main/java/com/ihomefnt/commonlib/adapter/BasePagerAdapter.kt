package com.ihomefnt.commonlib.adapter

import android.content.Context
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import java.util.ArrayList


class BasePagerAdapter(val context:Context,val mListViews: ArrayList<View> ):PagerAdapter(){

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = mListViews.size

    override  fun instantiateItem(container: View, position: Int): Any {
        val v = this.mListViews[position]
        (container as ViewPager).addView(v)
        return v
    }

   override fun destroyItem(container: View, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }

    override fun getItemPosition(`object`: Any): Int {
        return -2
    }
}
