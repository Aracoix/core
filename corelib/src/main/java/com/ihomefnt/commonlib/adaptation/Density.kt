package com.ihomefnt.commonlib.adaptation

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.Context
import android.content.res.Configuration
import android.util.DisplayMetrics
import com.ihomefnt.commonlib.CommonLibInit

//屏幕适配方案
object Density {

    private var appDensity = 0F
    private var appScaledDensity: Float = 0F
    private lateinit var appDisplayMetrics: DisplayMetrics
    private var barHeight: Int = 0
    private var density = 0f
    private var scaledDensity = 0F
    private var densityDpi: Int = 0
    private const val WIDTH = "width"
    private const val HEIGHT = "height"
    fun setDensity(application: Application) {
        //获取application的DisplayMetrics
        appDisplayMetrics = application.resources.displayMetrics
        //获取状态栏高度
        barHeight = getStatusBarHeight(application)

        if (appDensity == 0f) {
            //初始化的时候赋值
            density = appDisplayMetrics.density
            scaledDensity = appDisplayMetrics.scaledDensity
            densityDpi = appDisplayMetrics.densityDpi
            appDensity = appDisplayMetrics.density
            appScaledDensity = appDisplayMetrics.scaledDensity

            //添加字体变化的监听
            application.registerComponentCallbacks(object : ComponentCallbacks {
                override fun onConfigurationChanged(newConfig: Configuration?) {
                    //字体改变后,将appScaledDensity重新赋值
                    if (newConfig != null && newConfig.fontScale > 0) {
                        appScaledDensity = application.resources.displayMetrics.scaledDensity
                    }
                }

                override fun onLowMemory() {}
            })
        }
    }

    private fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen",
                "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
    //此方法在BaseActivity中做初始化
    fun setDefault(activity: Activity) {
        setAppOrientation(activity, WIDTH)
    }

//    恢复默认值 适用于当三方SDK提供的页面出问题的时候
    fun cancelThisPage(activity: Activity) {
        val activityDisplayMetrics = activity.resources.displayMetrics
        activityDisplayMetrics.density = density
        activityDisplayMetrics.scaledDensity = scaledDensity
        activityDisplayMetrics.densityDpi = densityDpi
    }
    /**
     * targetDensity
     * targetScaledDensity
     * targetDensityDpi
     * 这三个参数是统一修改过后的值
     * orientation:方向值,传入width或height
     */
    public fun setAppOrientation(activity: Activity, orientation: String) {

        val targetDensity: Float

        if (orientation == HEIGHT) {
            targetDensity = (appDisplayMetrics.heightPixels - barHeight) / CommonLibInit.height
        } else {
            targetDensity = appDisplayMetrics.widthPixels / CommonLibInit.wight
        }

        val targetScaledDensity = targetDensity * (appScaledDensity / appDensity)
        val targetDensityDpi = (160 * targetDensity).toInt()

        /**
         *
         * 最后在这里将修改过后的值赋给系统参数
         *
         * 只修改Activity的density值
         */
        val activityDisplayMetrics = activity.resources.displayMetrics
        activityDisplayMetrics?.density = targetDensity
        activityDisplayMetrics?.scaledDensity = targetScaledDensity
        activityDisplayMetrics?.densityDpi = targetDensityDpi
    }


}
