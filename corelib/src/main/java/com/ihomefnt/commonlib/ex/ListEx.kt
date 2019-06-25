package com.ihomefnt.commonlib.ex

/**
 * list 相关的exfun
 * Created by Aracoix on 2017/11/15.
 */
// List 是否不为空
inline fun List<*>?.isNotNull(): Boolean =(this!=null&&this.isNotEmpty())

// 把list 转成arrayList
inline  fun <reified T> List<T>.toArrayList():ArrayList<T>{
    val arrayList = arrayListOf<T>()
    arrayList.addAll(this)
    return arrayList
}
