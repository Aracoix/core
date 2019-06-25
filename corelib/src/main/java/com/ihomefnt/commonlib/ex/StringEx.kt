package com.ihomefnt.commonlib.ex

import com.ihomefnt.commonlib.utils.ToastUtil

fun String?.toSafe(): String = if (isNull()) "" else this!!

// 判空
fun String?.isNull(): Boolean = (this == null || "" == this || "null" == this || "nil" == this || "NULL" == this)
fun String?.isNotNull(): Boolean = !isNull()

fun String.toast(): Unit {
    ToastUtil.showShortToast(this)
}

