package com.ihomefnt.commonlib.ex

import android.app.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.ihomefnt.commonlib.CommonLibInit
import org.greenrobot.eventbus.EventBus

/**
 * activity 相关的exfun
 * Created by Aracoix on 2017/11/15.
 */
//启动activity
inline fun <reified T> Activity.start() {
    this.startActivity(Intent(this, T::class.java))
}
//启动activity
inline fun <reified T> androidx.fragment.app.Fragment.start() {
    this.startActivity(Intent(activity, T::class.java))
}
//启动activity
inline fun <reified T> android.app.Fragment.start() {
    this.startActivity(Intent(activity, T::class.java))
}

//安全的显示DialogFragment
 fun DialogFragment.showSafe(manager: FragmentManager?, tag: String = "dialog"){
    try {
        val mDismissed = DialogFragment::class.java.getDeclaredField("mDismissed")
        mDismissed.isAccessible = true
        mDismissed.set(this, false)
        val mShownByMe = DialogFragment::class.java.getDeclaredField("mShownByMe")
        mShownByMe.isAccessible = true
        mShownByMe.set(this, true)
        val ft = manager?.beginTransaction()
        ft?.add(this, tag)
        ft?.commitAllowingStateLoss()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
//安全的显示DialogFragment
 fun androidx.fragment.app.DialogFragment.showSafeX(manager: androidx.fragment.app.FragmentManager?, tag: String = "dialog"){
    try {
        val mDismissed = androidx.fragment.app.DialogFragment::class.java.getDeclaredField("mDismissed")
        mDismissed.isAccessible = true
        mDismissed.set(this, false)
        val mShownByMe = androidx.fragment.app.DialogFragment::class.java.getDeclaredField("mShownByMe")
        mShownByMe.isAccessible = true
        mShownByMe.set(this, true)
        val ft = manager?.beginTransaction()
        ft?.add(this, tag)
        ft?.commitAllowingStateLoss()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}




fun Activity.registerEventBus(){
    if (!EventBus.getDefault().isRegistered(this))
        EventBus.getDefault().register(this)

}
fun Fragment.registerEventBus(){
    if (!EventBus.getDefault().isRegistered(this))
    EventBus.getDefault().register(this)
}

fun Service.registerEventBus(){
    if (!EventBus.getDefault().isRegistered(this))
        EventBus.getDefault().register(this)
}
fun androidx.fragment.app.Fragment.registerEventBus(){
    if (!EventBus.getDefault().isRegistered(this))
    EventBus.getDefault().register(this)
}
fun Activity.unRegisterEventBus(){
    if (EventBus.getDefault().isRegistered(this))
    EventBus.getDefault().unregister(this)
}
fun Fragment.unRegisterEventBus(){
    if (EventBus.getDefault().isRegistered(this))
    EventBus.getDefault().unregister(this)
}
fun Service.unRegisterEventBus(){
    if (EventBus.getDefault().isRegistered(this))
        EventBus.getDefault().unregister(this)
}
fun androidx.fragment.app.Fragment.unRegisterEventBus(){
    if (EventBus.getDefault().isRegistered(this))
    EventBus.getDefault().unregister(this)
}



fun doWithActivity(func: (Activity) -> Unit){
    val currentActivity = CommonLibInit.activityLifecycleHelper.currentActivity
    if (currentActivity !=null) {
        func.invoke(currentActivity)
    }
}
fun doWithAppActivity(func: (AppCompatActivity) -> Unit){
    val currentActivity = CommonLibInit.activityLifecycleHelper.currentActivity
    if (currentActivity is AppCompatActivity) {
        func.invoke(currentActivity)
    }
}