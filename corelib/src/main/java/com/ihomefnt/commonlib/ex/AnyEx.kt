package com.ihomefnt.commonlib.ex

import com.ihomefnt.commonlib.utils.LogUtils
import org.greenrobot.eventbus.EventBus

//Event bus 发送
fun Any.sendPost(){
    EventBus.getDefault().post(this)
}
// yes方法 简化只有if的情况
fun Boolean?.yes(yes:()->Unit)  :Boolean?{
    if (this == true) yes.invoke()
    return this
}
// no方法 简化只有if的情况
fun Boolean?.no(no:()->Unit)  :Boolean?{
    if (this == false) no.invoke()
    return this
}

inline fun Any.log(s: Any) {
    LogUtils.e( "${this.javaClass.toString().split(".").last()} - ${LogUtils.DI(Exception())}----->$s")
}

inline fun Any.thread(func:()->Unit){
    func.invoke()
}
