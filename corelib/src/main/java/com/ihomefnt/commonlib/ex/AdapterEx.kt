package com.ihomefnt.commonlib.ex

import com.werb.library.MoreAdapter

fun MoreAdapter.refreshItem(data:Any): Unit {
        val indexOf = list.indexOf(data)
        if (indexOf!=-1) {
            notifyItemRangeChanged(indexOf,1,data)
        }
}