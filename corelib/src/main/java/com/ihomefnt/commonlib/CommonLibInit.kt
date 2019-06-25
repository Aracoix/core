package com.ihomefnt.commonlib

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import com.ihomefnt.commonlib.adaptation.Density
import com.ihomefnt.commonlib.utils.ActivityLifecycleHelper
import com.ihomefnt.commonlib.utils.ToastUtil

object CommonLibInit{
    lateinit var application:Application
    lateinit var baseUrl:String
    val activityLifecycleHelper: ActivityLifecycleHelper = ActivityLifecycleHelper()
    var height = 360f
    var wight = 360f
    var  clazz:Class<Any>?=null
    @SuppressLint("ShowToast")
    fun init(application: Application, baseUrl:String, height: Float=360f, wight: Float=360f){
        application.registerActivityLifecycleCallbacks(activityLifecycleHelper)
        this@CommonLibInit.application = application;
        this@CommonLibInit.baseUrl = baseUrl
        this@CommonLibInit.height = height
        this@CommonLibInit.wight = wight
        ToastUtil.myToast = Toast.makeText(application,"",Toast.LENGTH_SHORT)
        Density.setDensity(application)

    }
}
