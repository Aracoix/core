package com.ihomefnt.commonlib.ex

import com.ihomefnt.commonlib.CommonLibInit
import com.ihomefnt.commonlib.utils.DensityUtils

// dp to px
fun Float.toPx(): Int {
    return DensityUtils.dp2px(CommonLibInit.application,this)
}
// nullable to notNull
fun Int?.toSafe(): Int = this ?: 0
fun String?.toSafeLong():Long{
    return try {
        this?.toLong() ?: 0L
    } catch (e: Exception) {
        0L
    }
}

fun String?.toSafeDouble():Double{
    return try {
        this?.toDouble() ?: 0.0
    } catch (e: Exception) {
        0.0
    }
}
